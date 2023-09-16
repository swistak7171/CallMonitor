package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.ServerConfigurationDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel

fun ServerConfigurationDataModel.toDomainModel(): ServerConfigurationDomainModel {
    return ServerConfigurationDomainModel(
        host = host,
        port = port,
    )
}