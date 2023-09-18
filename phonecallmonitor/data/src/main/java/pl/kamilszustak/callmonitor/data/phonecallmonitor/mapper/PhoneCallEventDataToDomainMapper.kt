package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallEventDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel

/**
 * Maps a [PhoneCallEventDataModel] instance to a [PhoneCallEventDomainModel] instance.
 */
internal fun PhoneCallEventDataModel.toDomainModel(): PhoneCallEventDomainModel {
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