package pl.kamilszustak.callmonitor.server

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.server.plugin.ServerStatusMonitoringPlugin
import pl.kamilszustak.callmonitor.server.route.logRoute
import pl.kamilszustak.callmonitor.server.route.serverStatusRoute
import pl.kamilszustak.callmonitor.server.route.statusRoute

/**
 * Starts the phone call monitor server. It provides a REST API for retrieving information about:
 * - the server current status and the services it provides via [serverStatusRoute].
 * - the current status of the currently ongoing phone call via [statusRoute].
 * - the phone call log via [logRoute].
 *
 * It uses the [CIO] engine and JSON as the default content type. The server is stopped when the
 * the [CoroutineScope] is cancelled.
 */
suspend fun startPhoneCallMonitorServer(configuration: ServerConfigurationDomainModel) {
    coroutineScope {
        var engine: ApplicationEngine? = null
        launch {
            engine = embeddedServer(
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
                .start(wait = true)
        }.invokeOnCompletion {
            engine?.stop()
        }
    }
}