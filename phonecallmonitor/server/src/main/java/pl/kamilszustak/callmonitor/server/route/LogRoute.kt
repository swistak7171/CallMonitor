package pl.kamilszustak.callmonitor.server.route

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetAllPhoneCallLogEntriesUseCase
import pl.kamilszustak.callmonitor.server.mapper.toRemoteResponseModel

internal fun Route.logRoute() {
    val getAllPhoneCallLogEntriesUseCase by inject<GetAllPhoneCallLogEntriesUseCase>()

    get("/log") {
        val entries = getAllPhoneCallLogEntriesUseCase.execute()
            .map(PhoneCallLogEntryDomainModel::toRemoteResponseModel)
        call.respond(entries)
    }
}