package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.model.OngoingPhoneCallRemoteModel

fun OngoingPhoneCallDomainModel.toRemoteModel(): OngoingPhoneCallRemoteModel {
    return OngoingPhoneCallRemoteModel(
        phoneNumber = phoneNumber,
        contactName = contactName
    )
}