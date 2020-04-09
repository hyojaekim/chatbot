package com.kdu.life.application

import com.kdu.life.domain.LatitudeLongitude
import com.kdu.life.presentation.dto.AddressRequestDto
import com.kdu.life.presentation.dto.LocationDto
import com.kdu.life.util.UriFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class AddressService(val restTemplate: RestTemplate, val environment: Environment) {

    fun getLatitudeAndLongitude(addressRequestDto: AddressRequestDto): LocationDto {
        val key = environment.getProperty("geocoding.key") ?: throw IllegalArgumentException()
        val uri = UriFactory.createAddressUri(addressRequestDto.address, key)
        val latitudeLongitude = restTemplate.getForObject(uri, LatitudeLongitude::class.java)
                ?: throw IllegalArgumentException()
        return latitudeLongitude.get()
    }
}