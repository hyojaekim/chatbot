package com.kdu.common.util

import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.io.BufferedReader
import javax.servlet.http.HttpServletRequest

class KduJsonParser private constructor() {

    companion object {
        fun toJsonElement(request: HttpServletRequest): JsonElement {
            val reader: BufferedReader = request.reader
            val body: String = reader.use(BufferedReader::readText)
            return JsonParser.parseString(body)
        }

        fun find(jsonElement: JsonElement, route: List<String>): String {
            for (location in route) {
                jsonElement.asJsonObject.get(location)
            }
            return jsonElement.toString()
        }
    }
}