package dev.thesarfo.dockerui.core

import org.springframework.stereotype.Service
import com.fasterxml.jackson.databind.ObjectMapper
import dev.thesarfo.dockerui.domain.*

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

        val stats = runCommand("docker", "stats", "--no-stream", "--format", "{{.Name}}: {{.CPUPerc}} CPU, {{.MemUsage}} Mem")

        val cpuUsage = mutableMapOf<String, String>()
        val memoryUsage = mutableMapOf<String, String>()
        stats.lines().forEach { line ->
            if (line.isNotBlank()) {
                val parts = line.split(":")
                val containerName = parts[0].trim()
                val usageData = parts[1].trim().split(",")
                val cpu = usageData[0].split(" ")[1]
                val memory = usageData[1].split(" ")[1]

                cpuUsage[containerName] = cpu
                memoryUsage[containerName] = memory
            }
        }

        val running = containers.count { it.status?.contains("Up") == true }
        val stopped = containers.count { it.status?.contains("Exited") == true }
        val total = containers.size

        return mapOf(
            "running" to running,
            "stopped" to stopped,
            "total" to total,
            "cpu_usage" to cpuUsage,
            "memory_usage" to memoryUsage
        )
    }


    fun getRecentContainerEvents(): List<String> {
        return listOf(
            "Container 'web' started",
            "Container 'db' stopped",
            "New container 'nginx' created"
        )
    }

    fun listImages(): List<Image> {
        val output = runCommand("docker", "images", "--format", "{{json .}}")
        return output.lines()
            .filter { it.isNotBlank() }
            .map { json -> ObjectMapper().readValue(json, Image::class.java) }
    }

    fun removeImage(imageId: String) = runCommand("docker", "rmi", imageId)

    fun pullImage(imageName: String) = runCommand("docker", "pull", imageName)

    fun listVolumes(): List<Volume> {
        val output = runCommand("docker", "volume", "ls", "--format", "{{json .}}")
        return output.lines()
            .filter { it.isNotBlank() }
            .map { json -> ObjectMapper().readValue(json, Volume::class.java) }
    }

    fun createVolume(name: String) = runCommand("docker", "volume", "create", name)

    fun removeVolume(volumeName: String) = runCommand("docker", "volume", "rm", volumeName)

    fun inspectVolume(volumeName: String): VolumeDetails {
        val output = runCommand("docker", "volume", "inspect", volumeName)
        return ObjectMapper().readValue(output, Array<VolumeDetails>::class.java)[0]
    }

    fun listNetworks(): List<Network> {
        val output = runCommand("docker", "network", "ls", "--format", "{{json .}}")
        return output.lines()
            .filter { it.isNotBlank() }
            .map { json -> ObjectMapper().readValue(json, Network::class.java) }
    }

    fun createNetwork(name: String) = runCommand("docker", "network", "create", name)

    fun removeNetwork(networkId: String) = runCommand("docker", "network", "rm", networkId)

    fun inspectNetwork(networkId: String): NetworkDetails {
        val output = runCommand("docker", "network", "inspect", networkId)
        return ObjectMapper().readValue(output, Array<NetworkDetails>::class.java)[0]
    }

}
