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
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetAllPhoneCallLogEntriesUseCase
import pl.kamilszustak.callmonitor.server.mapper.toRemoteResponseModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.emptyPhoneCallLogJsonResponse
import pl.kamilszustak.callmonitor.server.phonecallmonitor.phoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.phoneCallLogEntryRemoteResponseModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.phoneCallLogJsonResponse
import pl.kamilszustak.callmonitor.server.route.logRoute
import kotlin.test.assertEquals

class LogRouteTest {

    // region Fields

    private val getAllPhoneCallLogEntriesUseCaseMock: GetAllPhoneCallLogEntriesUseCase = mockk()

    @get:Rule(order = 0)
    val mockProviderRule: MockProviderRule = MockProviderRule.create { klass ->
        mockkClass(klass)
    }

    @get:Rule(order = 1)
    val koinTestRule: KoinTestRule = KoinTestRule.create {
        modules(
            module {
                factory { getAllPhoneCallLogEntriesUseCaseMock }
            }
        )
    }

    // endregion

    // region Setup

    @After
    fun tearDown() {
        confirmVerified(getAllPhoneCallLogEntriesUseCaseMock)
    }

    // endregion

    // region Tests

    @Test
    fun `endpoint should respond with HTTP 200 and list of phone call log entries when phone call log is not empty`() =
        testApplication {
            mockkStatic(PhoneCallLogEntryDomainModel::toRemoteResponseModel) {
                // given
                testModule()
                coEvery {
                    getAllPhoneCallLogEntriesUseCaseMock.execute()
                } returns listOf(phoneCallLogEntryDomainModel)
                every {
                    phoneCallLogEntryDomainModel.toRemoteResponseModel()
                } returns phoneCallLogEntryRemoteResponseModel

                // when
                val response = client.get("/log")

                // then
                assertEquals(HttpStatusCode.OK, response.status)
                assertEquals(phoneCallLogJsonResponse, response.bodyAsText())

                coVerify(ordering = Ordering.SEQUENCE) {
                    getAllPhoneCallLogEntriesUseCaseMock.execute()
                    phoneCallLogEntryDomainModel.toRemoteResponseModel()
                }
            }
        }

    @Test
    fun `endpoint should respond with HTTP 200 and empty list of phone call log entries when phone call log is empty`() =
        testApplication {
            // given
            testModule()
            coEvery {
                getAllPhoneCallLogEntriesUseCaseMock.execute()
            } returns emptyList()

            // when
            val response = client.get("/log")

            // then
            assertEquals(HttpStatusCode.OK, response.status)
            assertEquals(emptyPhoneCallLogJsonResponse, response.bodyAsText())

            coVerify(ordering = Ordering.SEQUENCE) {
                getAllPhoneCallLogEntriesUseCaseMock.execute()
            }
        }

    // endregion

    // region Private Methods

    private fun TestApplicationBuilder.testModule() {
        install(ContentNegotiation) {
            json()
        }
        routing {
            logRoute()
        }
    }

    // endregion

}