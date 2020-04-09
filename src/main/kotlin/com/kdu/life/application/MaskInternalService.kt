package com.kdu.life.application

import com.kdu.life.domain.MaskStockInfo
import com.kdu.life.exception.MaskStockRequestFailException
import com.kdu.life.presentation.dto.LocationDto
import com.kdu.life.util.UriFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class MaskInternalService(val restTemplate: RestTemplate) {

    fun requestMaskStockInfo(locationDto: LocationDto): MaskStockInfo {
        val uri = UriFactory.createMaskStockUri(locationDto.latitude, locationDto.longitude)
        return restTemplate.getForObject(uri, MaskStockInfo::class.java) ?: throw MaskStockRequestFailException()
    }
}