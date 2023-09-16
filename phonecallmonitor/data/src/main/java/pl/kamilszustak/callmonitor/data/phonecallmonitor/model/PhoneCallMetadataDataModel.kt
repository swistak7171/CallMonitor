package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

import java.util.UUID

data class PhoneCallMetadataDataModel(
    val id: UUID,
    val timesQueried: Int = 0,
)
