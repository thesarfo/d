package dev.thesarfo.dockerui.core


import dev.thesarfo.dockerui.domain.Container
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class DockerController(val dockerService: DockerService) {

    @GetMapping("/")
    fun dashboard(model: Model): String {
        val stats = dockerService.getContainerStats()
        val events = dockerService.getRecentContainerEvents()

        model.addAttribute("stats", stats)
        model.addAttribute("events", events)
        return "dashboard"
    }


    @GetMapping("/containers")
    fun listContainers(model: Model): String {
        val containers: List<Container> = dockerService.listContainers()
        model.addAttribute("containers", containers)
        return "index"
    }

    @GetMapping("/containers/{id}")
    fun containerDetails(@PathVariable id: String, model: Model): String {
        val container = dockerService.listContainers().find { it.id == id }
        model.addAttribute("container", container)
        return "container-details"
    }

    @GetMapping("/containers/{id}/logs")
    fun getContainerLogs(@PathVariable id: String): ResponseEntity<String> {
        val logs = dockerService.getContainerLogs(id)
        return ResponseEntity.ok(logs)
    }


    @PostMapping("/containers/start/{id}")
    fun startContainer(@PathVariable id: String): ResponseEntity<String> {
        dockerService.startContainer(id)
        return ResponseEntity.ok("Container started")
    }

    @PostMapping("/containers/stop/{id}")
    fun stopContainer(@PathVariable id: String): ResponseEntity<String> {
        dockerService.stopContainer(id)
        return ResponseEntity.ok("Container stopped")
    }
}
