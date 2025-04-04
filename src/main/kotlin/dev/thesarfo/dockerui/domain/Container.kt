package dev.thesarfo.dockerui.domain


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unknown fields, just in case
data class Container(
    @JsonProperty("ID") val id: String,
    @JsonProperty("Image") val image: String,
    @JsonProperty("Command") val command: String? = null,
    @JsonProperty("Created") val created: String? = null,
    @JsonProperty("Status") val status: String? = null,
    @JsonProperty("Ports") val ports: String? = null,
    @JsonProperty("Names") val names: String? = null
)
