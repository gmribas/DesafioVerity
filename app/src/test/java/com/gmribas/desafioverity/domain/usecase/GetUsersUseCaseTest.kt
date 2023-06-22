package com.gmribas.desafioverity.domain.usecase

import com.gmribas.desafioverity.MainCoroutineRule
import com.gmribas.desafioverity.domain.model.UserDomain
import com.gmribas.desafioverity.repository.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetUsersUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var useCase: GetUsersUseCase

    private val repository: UserRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetUsersUseCase(repository)
    }

    @Test
    fun `When use case returns success`() {
        runBlocking {
            val usersFound: List<UserDomain> = mockk()

            // Given
            coEvery { repository.getUsers() } returns usersFound

            // When
            val response = useCase.process(GetUsersUseCase.Request).first()

            // Then
            assertEquals(GetUsersUseCase.Response(usersFound), response)
            coVerify(exactly = 1) { repository.getUsers() }
        }
    }

    @Test(expected = Exception::class)
    fun `When use case returns IllegalArgumentException expected`() {
        runBlocking {
            // Given
            val responseData = IllegalArgumentException()
            coEvery { repository.getUsers() } throws responseData

            // When
            useCase.process(GetUsersUseCase.Request).first()

            // Then
            coVerify(exactly = 1) { repository.getUsers() }
        }
    }
}