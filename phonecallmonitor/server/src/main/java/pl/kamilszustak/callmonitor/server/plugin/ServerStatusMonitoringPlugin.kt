package pl.kamilszustak.callmonitor.server.plugin

import io.ktor.server.application.ApplicationPlugin
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.ktor.ext.get
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.SetServerStatusUseCase

/**
 * A server plugin responsible for monitoring the server events and setting the server current
 * status.
 */
internal val ServerStatusMonitoringPlugin: ApplicationPlugin<Unit> =
    createApplicationPlugin("ServerStatusMonitoringPlugin") {
        val setServerStatusUseCase = application.get<SetServerStatusUseCase>()

        // Listen for the application started event and set the server status as started.
        on(MonitoringEvent(ApplicationStarted)) { _ ->
            application.launch {
                setServerStatusUseCase.execute(ServerStatusEventDomainModel.Started)
            }
        }

        // Listen for the application stopped event and set the server status as stopped.
        on(MonitoringEvent(ApplicationStopped)) { application ->
            // Make sure that the server status is set to "Stopped" when the application stops
            // before the application coroutine scope is cancelled.
            runBlocking {
                setServerStatusUseCase.execute(ServerStatusEventDomainModel.Stopped)
            }

            // Release resources and unsubscribe from events.
            application.environment.monitor.unsubscribe(ApplicationStarted) {}
            application.environment.monitor.unsubscribe(ApplicationStopped) {}
        }
    }