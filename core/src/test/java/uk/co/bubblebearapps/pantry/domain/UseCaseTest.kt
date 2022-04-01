package uk.co.bubblebearapps.pantry.domain

import arrow.core.Either
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

@ExperimentalCoroutinesApi
class UseCaseTest {

    @Test
    fun `a working Testcase results in a Right`() = runTest {
        val response = "and you know it"

        val worksFine = object : UseCase<String, String>() {
            override suspend fun doWork(params: String): String {
                return response
            }
        }

        worksFine("if you're happy") shouldBeEqualTo Either.Right(response)
    }

    @Test
    fun `a broken Testcase results in a Left`() = runTest {
        val exception = mockk<Exception>()

        val broken = object : UseCase<String, String>() {
            override suspend fun doWork(params: String): String {
                throw exception
            }
        }

        broken("clap your hands") shouldBeEqualTo Either.Left(exception)
    }
}
