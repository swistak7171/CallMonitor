package pl.kamilszustak.callmonitor.server.mapper

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.server.model.OngoingPhoneCallRemoteModel

fun OngoingPhoneCallDomainModel.toRemoteModel(): OngoingPhoneCallRemoteModel {
    return OngoingPhoneCallRemoteModel(
        phoneNumber = phoneNumber,
        contactName = contactName
    )
}