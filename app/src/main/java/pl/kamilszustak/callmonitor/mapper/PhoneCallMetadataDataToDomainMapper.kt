package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.PhoneCallMetadataDataModel
import pl.kamilszustak.callmonitor.model.PhoneCallMetadataDomainModel

fun PhoneCallMetadataDataModel.toDomainModel(): PhoneCallMetadataDomainModel {
    return PhoneCallMetadataDomainModel(
        timesQueried = timesQueried,
    )
}