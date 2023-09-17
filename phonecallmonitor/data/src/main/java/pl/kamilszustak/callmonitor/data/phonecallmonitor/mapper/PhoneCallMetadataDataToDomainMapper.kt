package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallMetadataDomainModel

internal fun PhoneCallMetadataDataModel.toDomainModel(): PhoneCallMetadataDomainModel {
    return PhoneCallMetadataDomainModel(
        timesQueried = timesQueried,
    )
}