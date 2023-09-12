package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.datasource.PhoneCallStateDataSource
import pl.kamilszustak.callmonitor.model.PhoneCallStateDataModel

class PhoneCallStateRepositoryImpl(
    private val phoneCallStateDataSource: PhoneCallStateDataSource,
) : PhoneCallStateRepository {

    override fun getRx(): Flow<PhoneCallStateDataModel> {
        return phoneCallStateDataSource.getRx()
    }

}