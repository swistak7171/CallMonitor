package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.PhoneCallStateDataModel
import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel

fun PhoneCallStateDataModel.toDomainModel(): PhoneCallStateDomainModel {
    return when (this) {
        is PhoneCallStateDataModel.StartedPhoneCall -> {
            PhoneCallStateDomainModel.StartedPhoneCall(
                id = id,
                timestamp = timestamp,
                phoneNumber = phoneNumber
            )
        }

        is PhoneCallStateDataModel.EndedPhoneCall -> {
            PhoneCallStateDomainModel.EndedPhoneCall(
                timestamp = timestamp,
                phoneNumber = phoneNumber
            )
        }
    }
}