package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

data class OngoingPhoneCallDomainModel(
    val id: String,
    val phoneNumber: String,
    val contactName: String?,
)
