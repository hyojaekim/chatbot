package com.kdu.util

import com.google.gson.JsonObject

class JsonFactory private constructor() {
    companion object {
        fun createUserInfo(id: String, campusName: String): String {
            val user = JsonObject()
            val campus = JsonObject()
            val result = JsonObject()

            user.addProperty("id", id)
            campus.addProperty("resolved", campusName)

            result.add("user", user)
            result.add("value", campus)
            return result.toString()
        }
    }
}