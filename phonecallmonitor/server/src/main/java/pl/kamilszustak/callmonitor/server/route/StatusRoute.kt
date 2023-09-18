package pl.kamilszustak.callmonitor.server.route

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetOngoingPhoneCallUseCase
import pl.kamilszustak.callmonitor.server.mapper.toRemoteResponseModel

/**
 * A route returning the ongoing phone call or an HTTP 404 error if there is no ongoing phone call.
 */
internal fun Route.statusRoute() {
    val getOngoingPhoneCallUseCase by inject<GetOngoingPhoneCallUseCase>()

    get("/status") {
        val ongoingPhoneCall = getOngoingPhoneCallUseCase.execute()

        if (ongoingPhoneCall != null) {
            val remoteModel = ongoingPhoneCall.toRemoteResponseModel()
            call.respond(remoteModel)
        } else {
            call.respond(HttpStatusCode.NotFound, "No ongoing phone call found")
        }
    }
}