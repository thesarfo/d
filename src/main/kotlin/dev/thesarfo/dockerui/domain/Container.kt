package dev.thesarfo.dockerui.domain


import com.fasterxml.jackson.annotation.JsonProperty

data class Container(
    @JsonProperty("ID") val id: String,
    @JsonProperty("Image") val image: String,
    @JsonProperty("Names") val names: String,
    @JsonProperty("Status") val status: String
)
