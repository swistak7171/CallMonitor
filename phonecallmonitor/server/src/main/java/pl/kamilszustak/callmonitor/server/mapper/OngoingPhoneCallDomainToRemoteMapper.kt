package pl.kamilszustak.callmonitor.server.mapper

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.server.model.OngoingPhoneCallRemoteResponseModel

/**
 * Maps a [OngoingPhoneCallDomainModel] instance to a [OngoingPhoneCallRemoteResponseModel] instance.
 */
internal fun OngoingPhoneCallDomainModel.toRemoteResponseModel(): OngoingPhoneCallRemoteResponseModel {
    return OngoingPhoneCallRemoteResponseModel(
        phoneNumber = phoneNumber,
        contactName = contactName
    )
}