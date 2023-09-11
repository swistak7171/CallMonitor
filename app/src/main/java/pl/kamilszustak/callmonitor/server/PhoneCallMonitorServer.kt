package pl.kamilszustak.callmonitor.server

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import pl.kamilszustak.callmonitor.server.route.logRoute
import pl.kamilszustak.callmonitor.server.route.rootRoute
import pl.kamilszustak.callmonitor.server.route.statusRoute

fun startPhoneCallMonitorServer() {
    embeddedServer(
        factory = CIO,
        port = 8080,
    ) {
        install(ContentNegotiation) {
            json()
        }

        applicationStartedMonitor()
        applicationStoppedMonitor()

        routing {
            rootRoute()
            statusRoute()
            logRoute()
        }
    }
        .start(wait = false)
}