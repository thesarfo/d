package dev.thesarfo.dockerui.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class IPAMConfig(
    val subnet: String?,
    val gateway: String?
)