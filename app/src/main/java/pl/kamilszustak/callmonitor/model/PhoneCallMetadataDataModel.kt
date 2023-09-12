package pl.kamilszustak.callmonitor.model

import java.util.UUID

data class PhoneCallMetadataDataModel(
    val id: UUID,
    val timesQueried: Int = 0,
)
