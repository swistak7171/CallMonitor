package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.callmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallEventDataModel
import pl.kamilszustak.callmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.repository.PhoneCallEventRepository

class GetPhoneCallEventUseCaseImpl(
    private val phoneCallEventRepository: PhoneCallEventRepository,
) : GetPhoneCallEventUseCase {

    override fun executeRx(): Flow<PhoneCallEventDomainModel> {
        return phoneCallEventRepository.getRx()
            .map(PhoneCallEventDataModel::toDomainModel)
    }

}