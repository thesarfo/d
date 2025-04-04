package dev.thesarfo.dockerui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerUiApplication

fun main(args: Array<String>) {
    runApplication<DockerUiApplication>(*args)
}
