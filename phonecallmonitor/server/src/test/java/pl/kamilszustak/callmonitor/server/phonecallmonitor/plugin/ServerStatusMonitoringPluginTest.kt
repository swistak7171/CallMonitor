package pl.kamilszustak.callmonitor.server.phonecallmonitor.plugin

import io.ktor.server.testing.testApplication
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.SetServerStatusUseCase
import pl.kamilszustak.callmonitor.server.plugin.ServerStatusMonitoringPlugin

class ServerStatusMonitoringPluginTest {

    // region Fields

    private val setServerStatusUseCaseMock: SetServerStatusUseCase = mockk()

    @get:Rule(order = 0)
    val mockProviderRule: MockProviderRule = MockProviderRule.create { klass ->
        mockkClass(klass)
    }

    @get:Rule(order = 1)
    val koinTestRule: KoinTestRule = KoinTestRule.create {
        modules(
            module {
                factory { setServerStatusUseCaseMock }
            }
        )
    }

    // endregion

    // region Tests

    @Test
    fun `'Ktor application should send server started event on its start and server stopped event on its stop'`() {
        // given
        coJustRun { setServerStatusUseCaseMock.execute(ServerStatusEventDomainModel.Started) }
        coJustRun { setServerStatusUseCaseMock.execute(ServerStatusEventDomainModel.Stopped) }

        // when
        testApplication {
            install(ServerStatusMonitoringPlugin)
        }

        // then
        coVerify {
            setServerStatusUseCaseMock.execute(ServerStatusEventDomainModel.Started)
            setServerStatusUseCaseMock.execute(ServerStatusEventDomainModel.Stopped)
        }
    }

    // endregion

}