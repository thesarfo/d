package dev.thesarfo.dockerui.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Image(
    @JsonProperty("ID") val id: String?,
    @JsonProperty("Repository") val repository: String?,
    @JsonProperty("Tag") val tag: String?,
    @JsonProperty("Created") val created: String?,
    @JsonProperty("Size") val size: String?
)
