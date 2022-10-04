package com.example.domain.usecases

import com.example.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import kotlin.test.assertEquals

class AuthenticateUserUseCaseTest {
    private val userRepository = mock<UserRepository>()

    companion object {
        const val USERNAME = "Dmitrii"
        const val PASSWORD = "password"
        const val REPEAT_PASSWORD = "password"
        const val INCORRECT_REPEAT_PASSWORD = "pasword"
        const val EMAIL = "testmail@gmail.com"
        const val EMPTY_EMAIL = ""
        const val EMPTY_PASSWORD = ""
    }

    @Test
    fun `should return success of create account`(): Unit = runBlocking {

        Mockito.`when`(userRepository.createAccount(EMAIL, PASSWORD, USERNAME)).thenReturn(true)

        val useCase = AuthenticateUserUseCaseImpl(userRepository)

        val actual = useCase.createAccount(EMAIL, PASSWORD, REPEAT_PASSWORD, USERNAME)
        val expected = 1

        assertEquals(expected, actual)
    }

    @Test
    fun `should return incorrect repeat password of create account`(): Unit = runBlocking {

        Mockito.`when`(userRepository.createAccount(EMAIL, PASSWORD, USERNAME)).thenReturn(true)

        val useCase = AuthenticateUserUseCaseImpl(userRepository)

        val actual = useCase.createAccount(EMAIL, PASSWORD, INCORRECT_REPEAT_PASSWORD, USERNAME)
        val expected = -1

        assertEquals(expected, actual)
    }

    @Test
    fun `should return error of create account`(): Unit = runBlocking {

        Mockito.`when`(userRepository.createAccount(EMAIL, PASSWORD, USERNAME)).thenReturn(false)

        val useCase = AuthenticateUserUseCaseImpl(userRepository)

        val actual = useCase.createAccount(EMAIL, PASSWORD, REPEAT_PASSWORD, USERNAME)
        val expected = 0

        assertEquals(expected, actual)
    }

    @Test
    fun `should return error of login because email empty`(): Unit = runBlocking {

        Mockito.`when`(userRepository.authenticate(EMAIL, PASSWORD)).thenReturn(true)

        val useCase = AuthenticateUserUseCaseImpl(userRepository)

        val actual = useCase.signIn(EMPTY_EMAIL, PASSWORD)
        val expected = false

        assertEquals(expected, actual)
    }

    @Test
    fun `should return error of login because password empty`(): Unit = runBlocking {

        Mockito.`when`(userRepository.authenticate(EMAIL, PASSWORD)).thenReturn(true)

        val useCase = AuthenticateUserUseCaseImpl(userRepository)

        val actual = useCase.signIn(EMAIL, EMPTY_PASSWORD)
        val expected = false

        assertEquals(expected, actual)
    }

    @Test
    fun `should return success of login`(): Unit = runBlocking {

        Mockito.`when`(userRepository.authenticate(EMAIL, PASSWORD)).thenReturn(true)

        val useCase = AuthenticateUserUseCaseImpl(userRepository)

        val actual = useCase.signIn(EMAIL, PASSWORD)
        val expected = true

        assertEquals(expected, actual)
    }

}