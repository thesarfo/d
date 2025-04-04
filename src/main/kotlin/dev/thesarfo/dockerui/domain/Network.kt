package dev.thesarfo.dockerui.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Network(
    @JsonProperty("ID") val id: String?,
    @JsonProperty("Name") val name: String?,
    @JsonProperty("Driver") val driver: String?,
    @JsonProperty("Scope") val scope: String?
)