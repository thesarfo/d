package dev.thesarfo.dockerui.domain


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Volume(
    @JsonProperty("Name") val name: String?,
    @JsonProperty("Driver") val driver: String?,
    @JsonProperty("Mountpoint") val mountpoint: String?,
    @JsonProperty("Scope") val scope: String?,
    @JsonProperty("Created") val created: String?,
    @JsonProperty("Options") val options: Map<String, String>?,
    @JsonProperty("Labels") val labels: Map<String, String>?
)

