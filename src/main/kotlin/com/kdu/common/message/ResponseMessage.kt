package com.kdu.common.message

import com.google.gson.JsonArray
import com.google.gson.JsonObject

class ResponseMessage private constructor() {

    data class Builder(
        private var version: String,
        private var template: JsonObject
    ) {
        constructor() : this("2.0", JsonObject()) {
            template.add(OUTPUTS, JsonArray())
        }

        fun simpleText(text: String) = apply {
            val simpleText = JsonObject()
            val textJson = JsonObject()
            textJson.addProperty(TEXT, text)
            simpleText.add(SIMPLE_TEXT, textJson)

            template.get(OUTPUTS).asJsonArray.add(simpleText)
        }

        fun build(): JsonObject {
            val result = JsonObject()
            result.addProperty(VERSION, version)
            result.add(TEMPLATE, template)
            return result
        }

        companion object {
            private const val VERSION = "version"
            private const val TEMPLATE = "template"
            private const val OUTPUTS = "outputs"
            private const val TEXT = "text"
            private const val SIMPLE_TEXT = "simpleText"
        }
    }
}