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

        fun quickReplies(cafeteriaNames: ArrayList<String>, subfix: String) = apply {
            template.add(QUICK_REPLIES, JsonArray())
            for (cafeteriaName in cafeteriaNames) {
                val quickReply = JsonObject()
                quickReply.addProperty(LABEL, cafeteriaName)
                quickReply.addProperty(ACTION, MESSAGE)
                quickReply.addProperty(MESSAGE_TEXT, cafeteriaName + subfix)
                template.get(QUICK_REPLIES).asJsonArray.add(quickReply)
            }
        }

        fun basicCards(basicCards: List<BasicCard>) = apply {
            template.add(OUTPUTS, JsonArray())
            val carousel = JsonObject()
            val items = JsonArray()
            for (basicCard in basicCards) {
                val item = JsonObject()
                item.addProperty(TITLE, basicCard.title)
                item.addProperty(DESCRIPTION, basicCard.description)
                items.add(item)
            }
            carousel.addProperty(TYPE, BASIC_CARD)
            carousel.add(ITEMS, items)

            val result = JsonObject()
            result.add(CAROUSEL, carousel)
            template.get(OUTPUTS).asJsonArray.add(result)
        }

        fun build(): JsonObject {
            val result = JsonObject()
            result.addProperty(VERSION, version)
            result.add(TEMPLATE, template)
            return result
        }

        companion object {
            private const val ACTION = "action"
            private const val BASIC_CARD = "basicCard"
            private const val CAROUSEL = "carousel"
            private const val DESCRIPTION = "description"
            private const val ITEMS = "items"
            private const val LABEL = "label"
            private const val MESSAGE = "message"
            private const val MESSAGE_TEXT = "messageText"
            private const val OUTPUTS = "outputs"
            private const val QUICK_REPLIES = "quickReplies"
            private const val SIMPLE_TEXT = "simpleText"
            private const val TEMPLATE = "template"
            private const val TEXT = "text"
            private const val TITLE = "title"
            private const val TYPE = "type"
            private const val VERSION = "version"
        }
    }
}