package pl.kamilszustak.callmonitor.server

import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.ApplicationStopped

fun Application.applicationStartedMonitor() {
    environment.monitor.subscribe(ApplicationStarted) {

    }
}

fun Application.applicationStoppedMonitor() {
    environment.monitor.subscribe(ApplicationStopped) {

    }
}