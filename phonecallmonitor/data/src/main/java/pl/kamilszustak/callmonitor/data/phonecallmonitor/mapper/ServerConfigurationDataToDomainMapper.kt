package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerConfigurationDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel

fun ServerConfigurationDataModel.toDomainModel(): ServerConfigurationDomainModel {
    return ServerConfigurationDomainModel(
        host = host,
        port = port,
    )
}