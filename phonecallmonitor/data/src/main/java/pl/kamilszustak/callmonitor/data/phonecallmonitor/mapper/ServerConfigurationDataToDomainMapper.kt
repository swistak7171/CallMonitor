package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerConfigurationDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel

/**
 * Maps the [ServerConfigurationDataModel] instance to the [ServerConfigurationDomainModel] instance.
 */
internal fun ServerConfigurationDataModel.toDomainModel(): ServerConfigurationDomainModel {
    return ServerConfigurationDomainModel(
        host = host,
        port = port,
    )
}