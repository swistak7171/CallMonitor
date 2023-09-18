package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel

/**
 * Maps a [OngoingPhoneCallDataModel] instance to an [OngoingPhoneCallDomainModel] instance with
 * the given [contactName].
 */
internal fun OngoingPhoneCallDataModel.toDomainModel(contactName: String?): OngoingPhoneCallDomainModel {
    return OngoingPhoneCallDomainModel(
        id = id,
        phoneNumber = phoneNumber,
        contactName = contactName,
    )
}