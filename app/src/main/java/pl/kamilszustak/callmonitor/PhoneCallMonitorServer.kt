package pl.kamilszustak.callmonitor

import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.routing.routing

fun startPhoneCallMonitorServer() {
    embeddedServer(
        factory = CIO,
        port = 8080,
    ) {
        routing {
        }
    }
        .start(wait = false)
}