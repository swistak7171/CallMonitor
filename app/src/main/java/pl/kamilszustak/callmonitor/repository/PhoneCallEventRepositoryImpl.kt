package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.datasource.PhoneCallEventDataSource
import pl.kamilszustak.callmonitor.model.PhoneCallEventDataModel

class PhoneCallEventRepositoryImpl(
    private val phoneCallEventDataSource: PhoneCallEventDataSource,
) : PhoneCallEventRepository {

    override fun getRx(): Flow<PhoneCallEventDataModel> {
        return phoneCallEventDataSource.getRx()
    }

}