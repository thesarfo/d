package dev.thesarfo.dockerui.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class NetworkDetails(
    val id: String?,
    val name: String?,
    val driver: String?,
    val scope: String?,
    val ipam: IPAM?,
    val containers: Map<String, NetworkContainer>?
)