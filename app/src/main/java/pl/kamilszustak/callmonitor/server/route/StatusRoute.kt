package pl.kamilszustak.callmonitor.server.route

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject
import pl.kamilszustak.callmonitor.mapper.toRemoteModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetOngoingPhoneCallUseCase

fun Route.statusRoute() {
    val getOngoingPhoneCallUseCase by inject<GetOngoingPhoneCallUseCase>()

    get("/status") {
        val ongoingPhoneCall = getOngoingPhoneCallUseCase.execute()

        if (ongoingPhoneCall != null) {
            val remoteModel = ongoingPhoneCall.toRemoteModel()
            call.respond(remoteModel)
        } else {
            call.respond(HttpStatusCode.NotFound, "No ongoing phone call found")
        }
    }
}