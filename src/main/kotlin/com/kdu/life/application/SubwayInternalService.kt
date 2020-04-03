package com.kdu.life.application

import com.kdu.life.domain.SubwayInfo
import com.kdu.life.exception.NotFoundSubwayException
import com.kdu.life.util.UriFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class SubwayInternalService(val restTemplate: RestTemplate, val environment: Environment) {

    fun find(station: String): SubwayInfo {
        val key = environment.getProperty("subway.key") ?: throw NotFoundSubwayException()
        val uri = UriFactory.getUri(key, station)
        val subwayInfo = restTemplate.getForObject(uri, SubwayInfo::class.java) ?: throw NotFoundSubwayException()
        if (!subwayInfo.isOk()) throw NotFoundSubwayException()
        return subwayInfo
    }
}