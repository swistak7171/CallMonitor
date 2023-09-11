package pl.kamilszustak.callmonitor.server.route

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.getAllRoutes
import pl.kamilszustak.callmonitor.model.RootRemoteModel
import pl.kamilszustak.callmonitor.model.ServiceRemoteModel

fun Route.rootRoute() {
    get("/") {
        val model = RootRemoteModel(
            startTimestamp = 1234,
            services = this@rootRoute.getAllRoutes()
                .map { route ->
                    ServiceRemoteModel(
                        name = route.selector.toString(),
                        uri = route.toString()
                    )
                }
        )
        call.respond(model)
    }
}