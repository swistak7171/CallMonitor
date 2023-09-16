package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import kotlinx.coroutines.coroutineScope
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallEventRepository
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository

interface MonitorPhoneCallsUseCase {
    suspend fun execute()
}

internal class MonitorPhoneCallsUseCaseImpl(
    private val phoneCallEventRepository: PhoneCallEventRepository,
    private val phoneCallRepository: PhoneCallRepository,
) : MonitorPhoneCallsUseCase {

    // region MonitorPhoneCallsUseCase Implementation

    override suspend fun execute() {
        coroutineScope {
            phoneCallEventRepository.getRx()
                .collect { event ->
                    when (event) {
                        is PhoneCallEventDomainModel.PhoneCallStart -> {
                            phoneCallRepository.setStarted(event)
                        }

                        is PhoneCallEventDomainModel.PhoneCallEnd -> {
                            phoneCallRepository.setEnded(event)
                        }
                    }
                }
        }
    }

    // endregion

}