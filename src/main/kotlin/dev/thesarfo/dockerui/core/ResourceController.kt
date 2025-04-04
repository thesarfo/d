package dev.thesarfo.dockerui.core


import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class ResourceController(val dockerService: DockerService) {

    @GetMapping("/images")
    fun listImages(model: Model): String {
        val images = dockerService.listImages()
        model.addAttribute("images", images)
        return "images"
    }

    @PostMapping("/images/pull")
    @ResponseBody
    fun pullImage(@RequestParam imageName: String): ResponseEntity<Map<String, Any>> {
        return try {
            dockerService.pullImage(imageName)
            ResponseEntity.ok(mapOf("success" to true, "message" to "Image pulled successfully"))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf("success" to false, "message" to (e.message ?: "Unknown error")))
        }
    }

    @DeleteMapping("/images/{id}")
    @ResponseBody
    fun removeImage(@PathVariable id: String): ResponseEntity<Map<String, Any>> {
        return try {
            dockerService.removeImage(id)
            ResponseEntity.ok(mapOf("success" to true, "message" to "Image removed successfully"))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf("success" to false, "message" to (e.message ?: "Unknown error")))
        }
    }

    @GetMapping("/volumes")
    fun listVolumes(model: Model): String {
        val volumes = dockerService.listVolumes()
        model.addAttribute("volumes", volumes)
        return "volumes"
    }

    @GetMapping("/volumes/{name}")
    fun volumeDetails(@PathVariable name: String, model: Model): String {
        val volume = dockerService.inspectVolume(name)
        model.addAttribute("volume", volume)
        return "volume-details"
    }

    @PostMapping("/volumes/create")
    @ResponseBody
    fun createVolume(@RequestParam name: String): ResponseEntity<Map<String, Any>> {
        return try {
            dockerService.createVolume(name)
            ResponseEntity.ok(mapOf("success" to true, "message" to "Volume created successfully"))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf("success" to false, "message" to (e.message ?: "Unknown error")))
        }
    }

    @DeleteMapping("/volumes/{name}")
    @ResponseBody
    fun removeVolume(@PathVariable name: String): ResponseEntity<Map<String, Any>> {
        return try {
            dockerService.removeVolume(name)
            ResponseEntity.ok(mapOf("success" to true, "message" to "Volume removed successfully"))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf("success" to false, "message" to (e.message ?: "Unknown error")))
        }
    }

    @GetMapping("/networks")
    fun listNetworks(model: Model): String {
        val networks = dockerService.listNetworks()
        model.addAttribute("networks", networks)
        return "networks"
    }

    @GetMapping("/networks/{id}")
    fun networkDetails(@PathVariable id: String, model: Model): String {
        val network = dockerService.inspectNetwork(id)
        model.addAttribute("network", network)
        return "network-details"
    }

    @PostMapping("/networks/create")
    @ResponseBody
    fun createNetwork(@RequestParam name: String): ResponseEntity<Map<String, Any>> {
        return try {
            dockerService.createNetwork(name)
            ResponseEntity.ok(mapOf("success" to true, "message" to "Network created successfully"))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf("success" to false, "message" to (e.message ?: "Unknown error")))
        }
    }

    @DeleteMapping("/networks/{id}")
    @ResponseBody
    fun removeNetwork(@PathVariable id: String): ResponseEntity<Map<String, Any>> {
        return try {
            dockerService.removeNetwork(id)
            ResponseEntity.ok(mapOf("success" to true, "message" to "Network removed successfully"))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf("success" to false, "message" to (e.message ?: "Unknown error")))
        }
    }
}