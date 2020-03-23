package com.kdu.common.message

import com.google.gson.JsonArray
import com.google.gson.JsonObject

class ResponseMessage private constructor() {

    data class Builder(
        private var version: String,
        private var template: JsonObject
    ) {
        constructor() : this("2.0", JsonObject())

        fun simpleText(text: String) = apply {
            template.add(OUTPUTS, JsonArray())
            val simpleText = JsonObject()
            val textJson = JsonObject()
            textJson.addProperty(TEXT, text)
            simpleText.add(SIMPLE_TEXT, textJson)

            template.get(OUTPUTS).asJsonArray.add(simpleText)
        }

        fun quickReplies(cafeteriaNames: ArrayList<String>) = apply {
            template.add("quickReplies", JsonArray())
            for (cafeteriaName in cafeteriaNames) {
                val quickReply = JsonObject()
                quickReply.addProperty("type", "text")
                quickReply.addProperty("label", cafeteriaName)
                quickReply.addProperty("message", cafeteriaName)
                template.get("quickReplies").asJsonArray.add(quickReply)
            }
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