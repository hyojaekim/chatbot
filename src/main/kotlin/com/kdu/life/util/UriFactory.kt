package com.kdu.life.util

import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

class UriFactory private constructor() {

    companion object {
        private const val HTTP = "http"
        private const val HTTPS = "https"
        private const val SUBWAY_HOST = "swopenapi.seoul.go.kr"
        private const val MASK_STOCK_HOST = "8oi9s0nnth.apigw.ntruss.com"
        private const val GEOCODING_HOST = "maps.googleapis.com"
        private const val SUBWAY_API_URI_PATH = "/api/subway/{key}/json/realtimeStationArrival/{minimum}/{maximum}/{station}"
        private const val MASK_STOCK_API_URI_PATH = "/corona19-masks/v1/storesByGeo/json"
        private const val GEOCODING_API_URI_PATH = "/maps/api/geocode/json"
        private const val MINIMUM_SIZE = "0"
        private const val MAXIMUM_SIZE = "5"
        private const val MAX_MITER = "2000"
        private const val LATITUDE = "lat"
        private const val LONGITUDE = "lng"
        private const val MITER = "m"
        private const val ADDRESS = "address"
        private const val KEY = "key"

        fun createSubwayUri(key: String, station: String): URI {
            return UriComponentsBuilder.newInstance()
                    .scheme(HTTP)
                    .host(SUBWAY_HOST)
                    .path(SUBWAY_API_URI_PATH)
                    .build().expand(key, MINIMUM_SIZE, MAXIMUM_SIZE, station)
                    .encode()
                    .toUri()
        }

        fun createMaskStockUri(latitude: Double, longitude: Double): URI {
            return UriComponentsBuilder.newInstance()
                    .scheme(HTTPS)
                    .host(MASK_STOCK_HOST)
                    .path(MASK_STOCK_API_URI_PATH)
                    .queryParam(LATITUDE, latitude)
                    .queryParam(LONGITUDE, longitude)
                    .queryParam(MITER, MAX_MITER)
                    .build()
                    .encode()
                    .toUri()
        }

        fun createAddressUri(address: String, key: String): URI {
            return UriComponentsBuilder.newInstance()
                    .scheme(HTTPS)
                    .host(GEOCODING_HOST)
                    .path(GEOCODING_API_URI_PATH)
                    .queryParam(ADDRESS, address)
                    .queryParam(KEY, key)
                    .build()
                    .encode()
                    .toUri()
        }
    }
}