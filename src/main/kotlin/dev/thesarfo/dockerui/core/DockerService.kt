package dev.thesarfo.dockerui.core

import org.springframework.stereotype.Service
import dev.thesarfo.dockerui.domain.Container
import com.fasterxml.jackson.databind.ObjectMapper

@Service
class DockerService {

    private fun runCommand(vararg command: String): String {
        val process = ProcessBuilder(*command).start()
        return process.inputStream.bufferedReader().readText()
    }

    fun listContainers(): List<Container> {
        val output = runCommand("docker", "ps", "-a", "--format", "{{json .}}")
        return output.lines()
            .filter { it.isNotBlank() }
            .map { json -> ObjectMapper().readValue(json, Container::class.java) }
    }

    fun startContainer(containerId: String) = runCommand("docker", "start", containerId)

    fun stopContainer(containerId: String) = runCommand("docker", "stop", containerId)

    fun removeContainer(containerId: String) = runCommand("docker", "rm", containerId)

    fun getContainerLogs(containerId: String): String {
        return runCommand("docker", "logs", "--tail", "100", containerId) // Fetch last 100 lines
    }
}
