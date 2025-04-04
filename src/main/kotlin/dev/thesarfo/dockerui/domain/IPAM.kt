package dev.thesarfo.dockerui.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class IPAM(
    val driver: String?,
    val config: List<IPAMConfig>?
)