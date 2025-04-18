package dev.thesarfo.dockerui.core

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        val module = SimpleModule()

        // Handle odd Docker options/labels format
        module.addDeserializer(Map::class.java, DockerMapDeserializer())
        mapper.registerModule(module)

        return mapper
    }
}

class DockerMapDeserializer : JsonDeserializer<Map<String, String>>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Map<String, String> {
        val value = p.text

        // If it's a normal JSON object, let Jackson handle it normally
        if (value.startsWith("{")) {
            return ctxt.readValue(p, Map::class.java) as Map<String, String>
        }

        // Handle special format like "com.docker.volume.anonymous="
        val result = mutableMapOf<String, String>()
        val parts = value.split(",")

        for (part in parts) {
            val keyValue = part.split("=", limit = 2)
            if (keyValue.size == 2) {
                result[keyValue[0]] = keyValue[1]
            } else if (keyValue.size == 1) {
                result[keyValue[0]] = ""
            }
        }

        return result
    }
}