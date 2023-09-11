package pl.kamilszustak.callmonitor.server.route

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryRemoteModel
import pl.kamilszustak.callmonitor.usecase.GetAllPhoneCallLogEntriesUC

fun Route.logRoute() {
    val getAllPhoneCallLogEntriesUC by inject<GetAllPhoneCallLogEntriesUC>()

    get("/log") {
        val entries = getAllPhoneCallLogEntriesUC.execute()
            .map { entry ->
                PhoneCallLogEntryRemoteModel(
                    beginningTimestamp = entry.startTimestamp,
                    duration = entry.duration.inWholeSeconds.toInt(),
                    phoneNumber = entry.phoneNumber,
                    contactName = entry.contactName
                )
            }
        call.respond(entries)
    }
}