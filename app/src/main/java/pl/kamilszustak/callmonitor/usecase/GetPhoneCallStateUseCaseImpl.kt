package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.callmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallStateDataModel
import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel
import pl.kamilszustak.callmonitor.repository.PhoneCallStateRepository

class GetPhoneCallStateUseCaseImpl(
    private val phoneCallStateRepository: PhoneCallStateRepository,
) : GetPhoneCallStateUseCase {

    override fun executeRx(): Flow<PhoneCallStateDomainModel> {
        return phoneCallStateRepository.getRx()
            .map(PhoneCallStateDataModel::toDomainModel)
    }

}