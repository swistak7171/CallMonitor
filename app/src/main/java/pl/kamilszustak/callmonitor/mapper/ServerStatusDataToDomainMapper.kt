package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.ServerStatusDataModel
import pl.kamilszustak.callmonitor.model.ServerStatusDomainModel

fun ServerStatusDataModel.toDomainModel(): ServerStatusDomainModel {
    return when (this) {
        is ServerStatusDataModel.Running -> ServerStatusDomainModel.Running(
            startTimestamp = this.startTimestamp,
        )

        is ServerStatusDataModel.Stopped -> ServerStatusDomainModel.Stopped
    }
}