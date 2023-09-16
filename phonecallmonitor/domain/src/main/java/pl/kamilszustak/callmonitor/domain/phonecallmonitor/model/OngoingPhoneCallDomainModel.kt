package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

data class OngoingPhoneCallDomainModel(
    val phoneNumber: String,
    val contactName: String?,
)
