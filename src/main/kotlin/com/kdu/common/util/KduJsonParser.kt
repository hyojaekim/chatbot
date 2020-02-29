package com.kdu.common.util

import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.io.BufferedReader
import javax.servlet.http.HttpServletRequest

class KduJsonParser private constructor() {

    companion object {
        fun toJsonElement(request: HttpServletRequest): JsonElement {
            val reader = request.reader
            val body: String = reader.use(BufferedReader::readText)
            return JsonParser.parseString(body)
        }
    }
}