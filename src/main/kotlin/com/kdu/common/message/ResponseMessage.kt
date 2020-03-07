package com.kdu.common.message

import com.google.gson.JsonObject

class ResponseMessage private constructor() {

    data class Builder(
            private val version: String = "2.0",
            private val template: JsonObject = JsonObject()
    ) {
        fun simpleText(text: String) = apply {
            val simpleText = JsonObject()
            simpleText.addProperty("text", text)
            template.add("simpleText", simpleText)
        }

        fun build(): JsonObject {
            val result = JsonObject()
            result.addProperty("version", version)
            result.add("template", template)
            return result
        }
    }
}