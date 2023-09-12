package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.PhoneCallStateDataModel
import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel

fun PhoneCallStateDomainModel.toDataModel(): PhoneCallStateDataModel {
    return when (this) {
        is PhoneCallStateDomainModel.StartedPhoneCall -> {
            PhoneCallStateDataModel.StartedPhoneCall(
                id = id,
                timestamp = timestamp,
                phoneNumber = phoneNumber
            )
        }

        is PhoneCallStateDomainModel.EndedPhoneCall -> {
            PhoneCallStateDataModel.EndedPhoneCall(
                timestamp = timestamp,
                phoneNumber = phoneNumber
            )
        }
    }
}