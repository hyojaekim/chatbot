package com.kdu.common.util

import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.io.BufferedReader
import javax.servlet.http.HttpServletRequest

class KduJsonParser private constructor() {

    companion object {
        private const val FIRST_ROUTE: Int = 0
        private const val QUOTATION_MARKS: String = "\""
        private const val EMPTY: String = ""

        fun toJsonElement(request: HttpServletRequest): JsonElement {
            val reader: BufferedReader = request.reader
            val body: String = reader.use(BufferedReader::readText)
            return JsonParser.parseString(body)
        }

        fun find(jsonElement: JsonElement, route: List<String>): String {
            var result: JsonElement = jsonElement.asJsonObject.get(route[FIRST_ROUTE])
            for (i in 1 until route.size) {
                result = result.asJsonObject.get(route[i])
            }
            return result.toString().replace(QUOTATION_MARKS, EMPTY)
        }

        fun findParams(jsonElement: JsonElement, key: String): String {
            return find(jsonElement, listOf("action", "params", key))
        }

        fun findUserId(jsonElement: JsonElement): String {
            return find(jsonElement, listOf("userRequest", "user", "id"))
        }
    }
}