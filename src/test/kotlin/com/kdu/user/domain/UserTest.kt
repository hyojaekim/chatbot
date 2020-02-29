package com.kdu.user.domain

import com.kdu.user.exception.NotFoundCampusException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class UserTest {

    @Test
    internal fun `정상적으로 유저를 생성한다`() {
        val user = User("kakoId", Campus.GOSEONG)
        assertThat(user).isNotNull
    }

    @Test
    internal fun `정상적으로 업데이트 한다`() {
        val user = User("kakaoId", Campus.GOSEONG)
        assertThat(user.campus).isEqualTo(Campus.GOSEONG)

        user.update(Campus.MUNMAK)
        assertThat(user.campus).isEqualTo(Campus.MUNMAK)
    }

    @Test
    internal fun `캠퍼스가 없다면 업데이트 할 수 없다`() {
        val user = User("kakaoId", Campus.GOSEONG)
        assertThat(user.campus).isEqualTo(Campus.GOSEONG)

        assertThrows(NotFoundCampusException::class.java) {
            user.update(Campus.EMPTY)
        }
    }
}