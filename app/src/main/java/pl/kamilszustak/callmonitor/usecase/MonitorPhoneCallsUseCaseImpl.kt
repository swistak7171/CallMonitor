package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.coroutineScope
import pl.kamilszustak.callmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository

class MonitorPhoneCallsUseCaseImpl(
    private val getPhoneCallEventUseCase: GetPhoneCallEventUseCase,
    private val phoneCallRepository: PhoneCallRepository,
) : MonitorPhoneCallsUseCase {

    override suspend fun execute() {
        coroutineScope {
            getPhoneCallEventUseCase.executeRx()
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

}