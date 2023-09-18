package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

/**
 * A model representing an ongoing phone call.
 */
data class OngoingPhoneCallDomainModel(
    val id: String,
    val phoneNumber: String,
    val contactName: String?,
)
