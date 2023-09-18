package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel

/**
 * Maps a [ServerStatusDataModel] instance to a [ServerStatusDomainModel] instance.
 */
internal fun ServerStatusDataModel.toDomainModel(): ServerStatusDomainModel {
    return when (this) {
        is ServerStatusDataModel.Running -> ServerStatusDomainModel.Running(
            startTimestamp = this.startTimestamp,
        )

        is ServerStatusDataModel.Stopped -> ServerStatusDomainModel.Stopped
    }
}