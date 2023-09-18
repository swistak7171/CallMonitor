package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

/**
 * A model representing metadata of a phone call. It contains information about the number of times
 * the phone call has been queried.
 */
data class PhoneCallMetadataDataModel(
    val id: String,
    val timesQueried: Int = 0,
)
