package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel

/**
 * Maps [PhoneCallEventDomainModel.PhoneCallStart] instance to the [OngoingPhoneCallDataModel]
 * instance.
 */
internal fun PhoneCallEventDomainModel.PhoneCallStart.toOngoingPhoneCallDataModel(): OngoingPhoneCallDataModel {
    return OngoingPhoneCallDataModel(
        id = id,
        startTimestamp = timestamp,
        phoneNumber = phoneNumber,
    )
}