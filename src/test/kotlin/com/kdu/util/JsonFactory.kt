package com.kdu.util

class JsonFactory private constructor() {
    companion object {
        fun createUserInfo(id: String, campusName: String): String {
            return "{\"action\":{\"params\":{\"campus\":\"${campusName}\"}},\"userRequest\":{\"user\":{\"id\":\"${id}\"}}}"
        }
    }
}