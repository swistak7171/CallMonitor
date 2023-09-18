package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

/**
 * A model representing metadata of a phone call. It contains information about the number of times
 * the phone call has been queried.
 */
@JvmInline
value class PhoneCallMetadataDomainModel(
    val timesQueried: Int,
)
