package dev.thesarfo.dockerui.domain


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Volume(
    @JsonProperty("Name") val name: String?,
    @JsonProperty("Driver") val driver: String?,
    @JsonProperty("Mountpoint") val mountpoint: String?
)

