package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallMetadataDomainModel

/**
 * Maps a [PhoneCallMetadataDataModel] instance to a [PhoneCallMetadataDomainModel] instance.
 */
internal fun PhoneCallMetadataDataModel.toDomainModel(): PhoneCallMetadataDomainModel {
    return PhoneCallMetadataDomainModel(
        timesQueried = timesQueried,
    )
}