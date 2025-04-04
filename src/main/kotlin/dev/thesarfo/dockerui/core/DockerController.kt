package dev.thesarfo.dockerui.core


import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/containers")
class DockerController(val dockerService: DockerService) {

    @GetMapping
    fun list() = dockerService.listContainers()

    @PostMapping("/{id}/start")
    fun start(@PathVariable id: String) = dockerService.startContainer(id)

    @PostMapping("/{id}/stop")
    fun stop(@PathVariable id: String) = dockerService.stopContainer(id)

    @PostMapping("/{id}/remove")
    fun remove(@PathVariable id: String) = dockerService.removeContainer(id)
}
