package com.kdu.life.util

import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

class UriFactory private constructor() {

    companion object {
        private const val HTTP = "http"
        private const val SUBWAY_HOST = "swopenapi.seoul.go.kr"
        private const val SUBWAY_API_URI_PATH = "/api/subway/{key}/json/realtimeStationArrival/{minimum}/{maximum}/{station}"
        private const val MINIMUM_SIZE = "0"
        private const val MAXIMUM_SIZE = "5"

        fun createSubwayUri(key: String, station: String): URI {
            return UriComponentsBuilder.newInstance()
                    .scheme(HTTP)
                    .host(SUBWAY_HOST)
                    .path(SUBWAY_API_URI_PATH)
                    .build().expand(key, MINIMUM_SIZE, MAXIMUM_SIZE, station)
                    .encode()
                    .toUri()
        }
    }
}