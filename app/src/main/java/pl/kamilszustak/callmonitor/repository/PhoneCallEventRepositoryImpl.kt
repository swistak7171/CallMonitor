package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.callmonitor.datasource.PhoneCallEventDataSource
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallEventRepository
import pl.kamilszustak.callmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallEventDataModel

class PhoneCallEventRepositoryImpl(
    private val phoneCallEventDataSource: PhoneCallEventDataSource,
) : PhoneCallEventRepository {

    override fun getRx(): Flow<PhoneCallEventDomainModel> {
        return phoneCallEventDataSource.getRx()
            .map(PhoneCallEventDataModel::toDomainModel)
    }

}