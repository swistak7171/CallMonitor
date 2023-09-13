package pl.kamilszustak.callmonitor.server.route

import io.ktor.server.application.call
import io.ktor.server.plugins.origin
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.getAllRoutes
import org.koin.ktor.ext.get
import pl.kamilszustak.callmonitor.model.RootRemoteModel
import pl.kamilszustak.callmonitor.model.ServerStatusDomainModel
import pl.kamilszustak.callmonitor.model.ServiceRemoteModel
import pl.kamilszustak.callmonitor.usecase.GetServerConfigurationUseCase
import pl.kamilszustak.callmonitor.usecase.GetServerStatusUseCase

fun Route.rootRoute() {
    val getServerConfigurationUseCase = get<GetServerConfigurationUseCase>()
    val getServerStatusUseCase = get<GetServerStatusUseCase>()

    get("/") {
        val configuration = getServerConfigurationUseCase.execute()
        val status = getServerStatusUseCase.execute()
        require(status is ServerStatusDomainModel.Running) {
            "Server is not running"
        }

        val scheme = call.request.origin.scheme
        val services = this@rootRoute.getAllRoutes()
            .map { route ->
                route.parent.toString()
                    .substringAfter('/')
            }
            .filter { it.isNotBlank() }
            .map { route ->
                ServiceRemoteModel(
                    name = route,
                    uri = "$scheme://${configuration.host}:${configuration.port}/$route"
                )
            }
        val rootModel = RootRemoteModel(
            startTimestamp = status.startTimestamp,
            services = services
        )

        call.respond(rootModel)
    }
}