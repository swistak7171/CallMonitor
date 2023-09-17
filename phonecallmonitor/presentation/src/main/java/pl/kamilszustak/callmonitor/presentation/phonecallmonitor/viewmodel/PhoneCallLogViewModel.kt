package pl.kamilszustak.callmonitor.presentation.phonecallmonitor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetAllPhoneCallLogEntriesUseCase
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerConfigurationUseCase
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.mapper.toViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogViewState

class PhoneCallLogViewModel(
    private val getServerConfigurationUseCase: GetServerConfigurationUseCase,
    private val getAllPhoneCallLogEntriesUseCase: GetAllPhoneCallLogEntriesUseCase,
) : ViewModel() {

    // region Fields

    private val _viewState: MutableStateFlow<PhoneCallLogViewState> =
        MutableStateFlow(PhoneCallLogViewState.Loading)
    val viewState: StateFlow<PhoneCallLogViewState> = _viewState.asStateFlow()

    // endregion

    // region Initializer

    init {
        loadData()
    }

    // endregion

    // region Private Methods

    private fun loadData() {
        val serverConfiguration = getServerConfigurationUseCase.execute()
        viewModelScope.launch {
            getAllPhoneCallLogEntriesUseCase.executeRx()
                .collect { logEntries ->
                    val logEntriesViewStates =
                        logEntries.map(PhoneCallLogEntryDomainModel::toViewState)

                    _viewState.value = PhoneCallLogViewState.Success(
                        serverHost = serverConfiguration.host,
                        serverPort = serverConfiguration.port,
                        entries = logEntriesViewStates
                    )
                }
        }
    }

    // endregion

}