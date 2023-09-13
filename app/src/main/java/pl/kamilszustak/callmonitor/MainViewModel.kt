package pl.kamilszustak.callmonitor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.kamilszustak.callmonitor.model.LogEntryViewState
import pl.kamilszustak.callmonitor.model.MainViewState
import pl.kamilszustak.callmonitor.usecase.GetAllPhoneCallLogEntriesUseCase
import pl.kamilszustak.callmonitor.usecase.GetServerConfigurationUseCase

class MainViewModel(
    getServerConfigurationUseCase: GetServerConfigurationUseCase,
    private val getAllPhoneCallLogEntriesUseCase: GetAllPhoneCallLogEntriesUseCase,
) : ViewModel() {

    private val _viewState: MutableStateFlow<MainViewState> = MutableStateFlow(MainViewState())
    val viewState: StateFlow<MainViewState> = _viewState.asStateFlow()

    init {
        val configuration = getServerConfigurationUseCase.execute()
        _viewState.update { state ->
            state.copy(
                serverHost = configuration.host,
                serverPort = configuration.port,
            )
        }

        viewModelScope.launch {
            getAllPhoneCallLogEntriesUseCase.executeRx()
                .collect { logEntries ->
                    val viewStates = logEntries.map { logEntry ->
                        LogEntryViewState(
                            text = logEntry.contactName ?: logEntry.phoneNumber,
                            duration = logEntry.duration,
                        )
                    }

                    _viewState.update { state ->
                        state.copy(
                            logEntries = viewStates
                        )
                    }
                }
        }
    }
}