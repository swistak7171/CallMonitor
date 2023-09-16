package pl.kamilszustak.callmonitor.server.plugin

import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent
import org.koin.ktor.ext.get
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.SetServerStatusUseCase

val ServerStatusMonitoringPlugin = createApplicationPlugin("ServerStatusMonitoringPlugin") {
    val setServerStatusUseCase = application.get<SetServerStatusUseCase>()

    on(MonitoringEvent(ApplicationStarted)) { application ->
        setServerStatusUseCase.execute(ServerStatusEventDomainModel.Started)
    }

    on(MonitoringEvent(ApplicationStopped)) { application ->
        setServerStatusUseCase.execute(ServerStatusEventDomainModel.Stopped)

        application.environment.monitor.unsubscribe(ApplicationStarted) {}
        application.environment.monitor.unsubscribe(ApplicationStopped) {}
    }
}