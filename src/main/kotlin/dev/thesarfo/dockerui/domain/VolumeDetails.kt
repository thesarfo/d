package dev.thesarfo.dockerui.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VolumeDetails(
    val name: String?,
    val driver: String?,
    val mountpoint: String?,
    val labels: Map<String, String>?,
    val options: Map<String, String>?
)