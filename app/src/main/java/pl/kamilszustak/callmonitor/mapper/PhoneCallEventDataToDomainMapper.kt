package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.PhoneCallEventDataModel
import pl.kamilszustak.callmonitor.model.PhoneCallEventDomainModel

fun PhoneCallEventDataModel.toDomainModel(): PhoneCallEventDomainModel {
    return when (this) {
        is PhoneCallEventDataModel.PhoneCallStart -> {
            PhoneCallEventDomainModel.PhoneCallStart(
                id = id,
                timestamp = timestamp,
                phoneNumber = phoneNumber
            )
        }

        is PhoneCallEventDataModel.PhoneCallEnd -> {
            PhoneCallEventDomainModel.PhoneCallEnd(
                timestamp = timestamp,
                phoneNumber = phoneNumber
            )
        }
    }
}