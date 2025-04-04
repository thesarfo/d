package dev.thesarfo.dockerui.core


import dev.thesarfo.dockerui.domain.Container
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class DockerController(val dockerService: DockerService) {

//    @GetMapping
//    fun list() = dockerService.listContainers()

    @GetMapping("/containers")
    fun listContainers(model: Model): String {
        val containers: List<Container> = dockerService.listContainers()
        model.addAttribute("containers", containers)
        return "index"
    }

    @PostMapping("/{id}/start")
    fun start(@PathVariable id: String) = dockerService.startContainer(id)

    @PostMapping("/{id}/stop")
    fun stop(@PathVariable id: String) = dockerService.stopContainer(id)

    @PostMapping("/{id}/remove")
    fun remove(@PathVariable id: String) = dockerService.removeContainer(id)
}
