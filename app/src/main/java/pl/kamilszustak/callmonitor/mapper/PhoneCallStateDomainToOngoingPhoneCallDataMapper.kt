package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.OngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel

fun PhoneCallEventDomainModel.PhoneCallStart.toOngoingPhoneCallDataModel(): OngoingPhoneCallDataModel {
    return OngoingPhoneCallDataModel(
        id = id,
        startTimestamp = timestamp,
        phoneNumber = phoneNumber,
    )
}