package pl.kamilszustak.callmonitor.domain.phonecallmonitor.di

import org.koin.core.Koin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetAllPhoneCallLogEntriesUseCase
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetAllPhoneCallLogEntriesUseCaseImpl
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetOngoingPhoneCallUseCase
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetOngoingPhoneCallUseCaseImpl
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerConfigurationUseCase
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerConfigurationUseCaseImpl
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerStatusUseCase
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerStatusUseCaseImpl
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.MonitorPhoneCallsUseCase
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.MonitorPhoneCallsUseCaseImpl
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.SetServerStatusUseCase
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.SetServerStatusUseCaseImpl

/**
 * A [Koin] module containing all dependencies for the domain layer.
 */
val phoneCallMonitorDomainModule: Module = module {
    factoryOf(::MonitorPhoneCallsUseCaseImpl)
        .bind<MonitorPhoneCallsUseCase>()
    factoryOf(::GetAllPhoneCallLogEntriesUseCaseImpl)
        .bind<GetAllPhoneCallLogEntriesUseCase>()
    factoryOf(::GetOngoingPhoneCallUseCaseImpl)
        .bind<GetOngoingPhoneCallUseCase>()
    factoryOf(::GetServerConfigurationUseCaseImpl)
        .bind<GetServerConfigurationUseCase>()
    factoryOf(::GetServerStatusUseCaseImpl)
        .bind<GetServerStatusUseCase>()
    factoryOf(::SetServerStatusUseCaseImpl)
        .bind<SetServerStatusUseCase>()
}