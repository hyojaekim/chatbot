package com.kdu.user.application

import com.kdu.user.domain.EventUser
import com.kdu.user.domain.repository.EventUserRepository
import com.kdu.user.exception.DuplicateKduEventException
import com.kdu.user.exception.EventCloseException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class KduEventInternalService(private val eventUserRepository: EventUserRepository) {

    fun isWinner(kakaoId: String): Boolean {
        checkFullEventUser()
        checkDuplicateEvent(kakaoId)
        val eventUser = eventUserRepository.save(EventUser(kakaoId))
        return eventUser.winner
    }

    private fun checkDuplicateEvent(kakaoId: String) {
        if (eventUserRepository.existsByKakaoId(kakaoId)) {
            throw DuplicateKduEventException()
        }
    }

    private fun checkFullEventUser() {
        if (eventUserRepository.countAllByWinnerTrue() == MAX_WINNERS) {
            throw EventCloseException()
        }
    }

    companion object {
        private const val MAX_WINNERS = 2
    }
}