package pl.kamilszustak.callmonitor.server.route

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject
import pl.kamilszustak.callmonitor.model.OngoingPhoneCallRemoteModel
import pl.kamilszustak.callmonitor.usecase.GetOngoingPhoneCallUseCase

fun Route.statusRoute() {
    val getOngoingPhoneCallUseCase by inject<GetOngoingPhoneCallUseCase>()

    get("/status") {
        val ongoingPhoneCall = getOngoingPhoneCallUseCase.execute()

        if (ongoingPhoneCall != null) {
            val remoteModel = OngoingPhoneCallRemoteModel(
                phoneNumber = ongoingPhoneCall.phoneNumber,
                contactName = ongoingPhoneCall.contactName
            )
            call.respond(remoteModel)
        } else {
            call.respond(HttpStatusCode.NotFound, "No ongoing phone call found")
        }
    }
}