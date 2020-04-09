package com.kdu.life.domain

import com.kdu.life.presentation.dto.LocationDto

data class LatitudeLongitude(
        private val results: List<Result>,
        private val status: String
) {
    data class Result(val geometry: Geometry)
    data class Geometry(val location: Location)
    data class Location(val lat: Double, val lng: Double)

    init {
        if (this.status != "OK") throw IllegalArgumentException()
    }

    fun get(): LocationDto {
        val location = this.results[0].geometry.location
        return LocationDto(location.lat, location.lng)
    }
}
