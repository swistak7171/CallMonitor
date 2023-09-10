package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.coroutineScope
import pl.kamilszustak.callmonitor.model.PhoneCallState

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
                        is PhoneCallState.Started -> {
                            setPhoneCallStartedUseCase.execute(state.phoneNumber, state.timestamp)
                        }

                        is PhoneCallState.Ended -> {
                            setPhoneCallEndedUseCase.execute(state.phoneNumber, state.timestamp)
                        }
                    }
                }
        }
    }

}