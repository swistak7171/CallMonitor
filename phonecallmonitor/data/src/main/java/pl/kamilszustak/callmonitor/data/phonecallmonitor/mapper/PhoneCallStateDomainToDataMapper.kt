package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallEventDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel

fun PhoneCallEventDomainModel.toDataModel(): PhoneCallEventDataModel {
    return when (this) {
        is PhoneCallEventDomainModel.PhoneCallStart -> {
            PhoneCallEventDataModel.PhoneCallStart(
                id = id,
                timestamp = timestamp,
                phoneNumber = phoneNumber
            )
        }

        is PhoneCallEventDomainModel.PhoneCallEnd -> {
            PhoneCallEventDataModel.PhoneCallEnd(
                timestamp = timestamp,
                phoneNumber = phoneNumber
            )
        }
    }
}