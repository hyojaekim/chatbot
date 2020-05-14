package com.kdu.util

class JsonFactory private constructor() {
    companion object {
        fun createUserInfo(id: String, campusName: String): String {
            return "{\"action\":{\"params\":{\"campus\":\"${campusName}\"}},\"userRequest\":{\"user\":{\"id\":\"${id}\"}}}"
        }

        fun createParams(key: String, value: String): String {
            return "{\"action\":{\"params\":{\"${key}\":\"${value}\"}},\"userRequest\":{\"user\":{\"id\":\"testId\"}}}"
        }

        fun createMaskInfo(year: String): String {
            return "{\"userRequest\":{\"utterance\":\"${year}\"}}"
        }

        fun createKakaoId(id: String): String {
            return "{\"userRequest\":{\"user\":{\"id\":\"${id}\"}}}"
        }
    }
}