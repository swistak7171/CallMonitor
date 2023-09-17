package pl.kamilszustak.callmonitor.server.phonecallmonitor.route

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.get
import io.ktor.server.testing.TestApplicationBuilder
import io.ktor.server.testing.testApplication
import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerConfigurationUseCase
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerStatusUseCase
import pl.kamilszustak.callmonitor.server.phonecallmonitor.serverConfigurationDomainModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.serverStatusJsonResponse
import pl.kamilszustak.callmonitor.server.phonecallmonitor.serverStatusRunningDomainModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.testRoutes
import pl.kamilszustak.callmonitor.server.route.serverStatusRoute
import kotlin.test.assertEquals

class ServerStatusRouteTest {

    // region Fields

    private val getServerConfigurationUseCaseMock: GetServerConfigurationUseCase = mockk()
    private val getServerStatusUseCaseMock: GetServerStatusUseCase = mockk()

    @get:Rule(order = 0)
    val mockProviderRule: MockProviderRule = MockProviderRule.create { klass ->
        mockkClass(klass)
    }

    @get:Rule(order = 1)
    val koinTestRule: KoinTestRule = KoinTestRule.create {
        modules(
            module {
                factory { getServerConfigurationUseCaseMock }
                factory { getServerStatusUseCaseMock }
            }
        )
    }

    // endregion

    // region Setup

    @Before
    fun setUp() {
        every { getServerConfigurationUseCaseMock.execute() } returns serverConfigurationDomainModel
        coEvery { getServerStatusUseCaseMock.execute() } returns serverStatusRunningDomainModel
    }

    @After
    fun tearDown() {
        confirmVerified(
            getServerConfigurationUseCaseMock,
            getServerStatusUseCaseMock,
        )
    }

    // endregion

    // region Tests

    @Test
    fun `endpoint should respond with server status information containing its start timestamp and services it offers`() =
        testApplication {
            // given
            testModule()

            // when
            val response = client.get("/")

            // then
            assertEquals(HttpStatusCode.OK, response.status)
            assertEquals(serverStatusJsonResponse, response.bodyAsText())

            coVerify(ordering = Ordering.SEQUENCE) {
                getServerConfigurationUseCaseMock.execute()
                getServerStatusUseCaseMock.execute()
            }
        }

    // endregion

    // region Private Methods

    private fun TestApplicationBuilder.testModule() {
        install(ContentNegotiation) {
            json()
        }
        routing {
            serverStatusRoute()
            testRoutes.forEach { route ->
                get(route) {}
            }
        }
    }

    // endregion

}