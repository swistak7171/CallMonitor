package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.coroutineScope
import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel

class MonitorPhoneCallsUseCaseImpl(
    private val getPhoneCallStateUseCase: GetPhoneCallStateUseCase,
    private val setPhoneCallStartedUseCase: SetPhoneCallStartedUseCase,
    private val setPhoneCallEndedUseCase: SetPhoneCallEndedUseCase,
) : MonitorPhoneCallsUseCase {

    override suspend fun execute() {
        coroutineScope {
            getPhoneCallStateUseCase.executeRx()
                .collect { state ->
                    when (state) {
                        is PhoneCallStateDomainModel.StartedPhoneCall -> {
                            setPhoneCallStartedUseCase.execute(state)
                        }

                        is PhoneCallStateDomainModel.EndedPhoneCall -> {
                            setPhoneCallEndedUseCase.execute(state)
                        }
                    }
                }
        }
    }

}