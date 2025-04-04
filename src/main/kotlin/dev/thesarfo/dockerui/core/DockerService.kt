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
        return runCommand("docker", "logs", "--tail", "100", containerId)
    }

    fun getContainerStats(): Map<String, Any> {
        val containers = listContainers()

        // Get the stats for each container
        val stats = runCommand("docker", "stats", "--no-stream", "--format", "{{.Name}}: {{.CPUPerc}} CPU, {{.MemUsage}} Mem")

        // Parse the stats
        val cpuUsage = mutableMapOf<String, String>()
        val memoryUsage = mutableMapOf<String, String>()
        stats.lines().forEach { line ->
            if (line.isNotBlank()) {
                val parts = line.split(":")
                val containerName = parts[0].trim()
                val usageData = parts[1].trim().split(",")
                val cpu = usageData[0].split(" ")[1]  // CPU usage percentage
                val memory = usageData[1].split(" ")[1]  // Memory usage

                cpuUsage[containerName] = cpu
                memoryUsage[containerName] = memory
            }
        }

        // Get counts for running and stopped containers
        val running = containers.count { it.status?.contains("Up") == true }
        val stopped = containers.count { it.status?.contains("Exited") == true }
        val total = containers.size

        return mapOf(
            "running" to running,
            "stopped" to stopped,
            "total" to total,
            "cpu_usage" to cpuUsage,  // Return actual CPU usage per container
            "memory_usage" to memoryUsage  // Return actual memory usage per container
        )
    }


    fun getRecentContainerEvents(): List<String> {
        return listOf(
            "Container 'web' started",
            "Container 'db' stopped",
            "New container 'nginx' created"
        )
    }


}
