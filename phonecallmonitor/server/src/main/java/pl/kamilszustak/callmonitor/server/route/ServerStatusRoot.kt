package pl.kamilszustak.callmonitor.server.route

import io.ktor.server.application.call
import io.ktor.server.plugins.origin
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.getAllRoutes
import org.koin.ktor.ext.inject
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerConfigurationUseCase
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerStatusUseCase
import pl.kamilszustak.callmonitor.server.model.ServerStatusRemoteResponseModel
import pl.kamilszustak.callmonitor.server.model.ServiceRemoteResponseModel

/**
 * A route returning the server current status and its services.
 */
internal fun Route.serverStatusRoute() {
    val getServerConfigurationUseCase by inject<GetServerConfigurationUseCase>()
    val getServerStatusUseCase by inject<GetServerStatusUseCase>()

    get("/") {
        val configuration = getServerConfigurationUseCase.execute()
        val status = getServerStatusUseCase.execute()
        require(status is ServerStatusDomainModel.Running) {
            "Server is not running"
        }

        val responseModel = createResponse(
            allRoutes = this@serverStatusRoute.getAllRoutes(),
            serverStatus = status,
            serverConfiguration = configuration,
            scheme = call.request.origin.scheme
        )

        call.respond(responseModel)
    }
}

/**
 * Returns a response model containing the server status and its services. The services are
 * determined by the routes registered in the application. The result response does not contain
 * the root route.
 */
private fun createResponse(
    allRoutes: List<Route>,
    serverStatus: ServerStatusDomainModel.Running,
    serverConfiguration: ServerConfigurationDomainModel,
    scheme: String
): ServerStatusRemoteResponseModel {
    return allRoutes
        .map { route ->
            route.parent
                .toString()
                .substringAfter('/')
        }
        .filter { it.isNotBlank() }
        .map { route ->
            ServiceRemoteResponseModel(
                name = route,
                uri = "$scheme://${serverConfiguration.host}:${serverConfiguration.port}/$route"
            )
        }
        .let { services ->
            ServerStatusRemoteResponseModel(
                startTimestamp = serverStatus.startTimestamp,
                services = services
            )
        }
}