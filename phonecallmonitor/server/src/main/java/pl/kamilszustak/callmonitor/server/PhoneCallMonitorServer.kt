package pl.kamilszustak.callmonitor.server

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerConfigurationUseCase
import pl.kamilszustak.callmonitor.server.plugin.ServerStatusMonitoringPlugin
import pl.kamilszustak.callmonitor.server.route.logRoute
import pl.kamilszustak.callmonitor.server.route.serverStatusRoute
import pl.kamilszustak.callmonitor.server.route.statusRoute

fun KoinComponent.startPhoneCallMonitorServer() {
    val getServerConfigurationUseCase = get<GetServerConfigurationUseCase>()
    val configuration = getServerConfigurationUseCase.execute()

    embeddedServer(
        factory = CIO,
        port = configuration.port,
        host = configuration.host
    ) {
        install(ContentNegotiation) {
            json()
        }
        install(ServerStatusMonitoringPlugin)

        routing {
            serverStatusRoute()
            statusRoute()
            logRoute()
        }
    }
        .start(wait = false)
}