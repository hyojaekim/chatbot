package com.kdu.life.application

import com.kdu.common.message.BasicCard
import com.kdu.life.presentation.dto.SubwayInfoRequestDto
import org.springframework.stereotype.Service

@Service
class SubwayService(val subwayInternalService: SubwayInternalService) {

    fun find(subwayInfoRequestDto: SubwayInfoRequestDto): List<BasicCard> {
        val subwayInfo = subwayInternalService.find(subwayInfoRequestDto.station)
        return subwayInfo.realtimeArrivalList
                .map { subway -> BasicCard(subway.getStationName(), subway.toString()) }
                .toList()
    }
}