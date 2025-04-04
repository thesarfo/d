package dev.thesarfo.dockerui.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class NetworkContainer(
    val name: String?,
    val endpointId: String?,
    val macAddress: String?,
    val ipv4Address: String?,
    val ipv6Address: String?
)