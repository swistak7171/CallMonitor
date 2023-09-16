package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

import java.util.UUID

data class OngoingPhoneCallDomainModel(
    val id: UUID,
    val phoneNumber: String,
    val contactName: String?,
)
