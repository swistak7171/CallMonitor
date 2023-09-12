package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.OngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel

fun PhoneCallStateDomainModel.StartedPhoneCall.toOngoingPhoneCallDataModel(): OngoingPhoneCallDataModel {
    return OngoingPhoneCallDataModel(
        id = id,
        timestamp = timestamp,
        phoneNumber = phoneNumber,
    )
}