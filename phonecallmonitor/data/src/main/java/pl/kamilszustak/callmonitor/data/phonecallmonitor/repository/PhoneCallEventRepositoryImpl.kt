package pl.kamilszustak.callmonitor.data.phonecallmonitor.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallEventDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallEventDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallEventRepository

internal class PhoneCallEventRepositoryImpl(
    private val phoneCallEventDataSource: PhoneCallEventDataSource,
) : PhoneCallEventRepository {

    override fun getRx(): Flow<PhoneCallEventDomainModel> {
        return phoneCallEventDataSource.getRx()
            .map(PhoneCallEventDataModel::toDomainModel)
    }

}