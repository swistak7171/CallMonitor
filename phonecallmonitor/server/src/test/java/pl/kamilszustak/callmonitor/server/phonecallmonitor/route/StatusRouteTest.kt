package pl.kamilszustak.callmonitor.server.phonecallmonitor.route

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.testing.TestApplicationBuilder
import io.ktor.server.testing.testApplication
import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.mockkStatic
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetOngoingPhoneCallUseCase
import pl.kamilszustak.callmonitor.server.mapper.toRemoteResponseModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.ongoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.ongoingPhoneCallJsonResponse
import pl.kamilszustak.callmonitor.server.phonecallmonitor.ongoingPhoneCallRemoteResponseModel
import pl.kamilszustak.callmonitor.server.route.statusRoute
import kotlin.test.assertEquals

class StatusRouteTest {

    // region Fields

    private val getOngoingPhoneCallUseCaseMock: GetOngoingPhoneCallUseCase = mockk()

    @get:Rule(order = 0)
    val mockProviderRule: MockProviderRule = MockProviderRule.create { klass ->
        mockkClass(klass)
    }

    @get:Rule(order = 1)
    val koinTestRule: KoinTestRule = KoinTestRule.create {
        modules(
            module {
                factory { getOngoingPhoneCallUseCaseMock }
            }
        )
    }

    // endregion

    // region Setup

    @After
    fun tearDown() {
        confirmVerified(getOngoingPhoneCallUseCaseMock)
    }

    // endregion

    // region Tests

    @Test
    fun `endpoint should respond with HTTP 200 and ongoing phone call when there is one currently`() =
        testApplication {
            mockkStatic(OngoingPhoneCallDomainModel::toRemoteResponseModel) {
                // given
                testModule()
                coEvery {
                    getOngoingPhoneCallUseCaseMock.execute()
                } returns ongoingPhoneCallDomainModel
                every {
                    ongoingPhoneCallDomainModel.toRemoteResponseModel()
                } returns ongoingPhoneCallRemoteResponseModel

                // when
                val response = client.get("/status")

                // then
                assertEquals(HttpStatusCode.OK, response.status)
                assertEquals(ongoingPhoneCallJsonResponse, response.bodyAsText())

                coVerify(ordering = Ordering.SEQUENCE) {
                    getOngoingPhoneCallUseCaseMock.execute()
                    ongoingPhoneCallDomainModel.toRemoteResponseModel()
                }
            }
        }

    @Test
    fun `endpoint should respond with HTTP 404 when there is no ongoing phone call`() =
        testApplication {
            // given
            testModule()
            coEvery { getOngoingPhoneCallUseCaseMock.execute() } returns null

            // when
            val response = client.get("/status")

            // then
            assertEquals(HttpStatusCode.NotFound, response.status)

            coVerify(ordering = Ordering.SEQUENCE) {
                getOngoingPhoneCallUseCaseMock.execute()
            }
        }

    // endregion

    // region Private Methods

    private fun TestApplicationBuilder.testModule() {
        install(ContentNegotiation) {
            json()
        }
        routing {
            statusRoute()
        }
    }

    // endregion

}