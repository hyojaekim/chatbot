package com.kdu.life.domain

data class SubwayInfo(
        val errorMessage: SubwayErrorMessage,
        val realtimeArrivalList: List<Subway>
) {
    fun isOk(): Boolean {
        return errorMessage.status == 200
    }
}