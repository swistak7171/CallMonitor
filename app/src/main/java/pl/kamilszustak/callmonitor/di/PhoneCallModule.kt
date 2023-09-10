package pl.kamilszustak.callmonitor.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.datasource.ContactNameDataSource
import pl.kamilszustak.callmonitor.datasource.ContactNameDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.datasource.OngoingPhoneCallDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallLogDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.PhoneCallStateDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallStateDataSourceImpl
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository
import pl.kamilszustak.callmonitor.repository.PhoneCallRepositoryImpl
import pl.kamilszustak.callmonitor.repository.PhoneCallStateRepository
import pl.kamilszustak.callmonitor.repository.PhoneCallStateRepositoryImpl
import pl.kamilszustak.callmonitor.usecase.GetPhoneCallStateUseCase
import pl.kamilszustak.callmonitor.usecase.GetPhoneCallStateUseCaseImpl
import pl.kamilszustak.callmonitor.usecase.MonitorPhoneCallsUseCase
import pl.kamilszustak.callmonitor.usecase.MonitorPhoneCallsUseCaseImpl
import pl.kamilszustak.callmonitor.usecase.SetPhoneCallEndedUseCase
import pl.kamilszustak.callmonitor.usecase.SetPhoneCallEndedUseCaseImpl
import pl.kamilszustak.callmonitor.usecase.SetPhoneCallStartedUseCase
import pl.kamilszustak.callmonitor.usecase.SetPhoneCallStartedUseCaseImpl

val phoneCallModule: Module = module {

    factoryOf(::GetPhoneCallStateUseCaseImpl)
        .bind<GetPhoneCallStateUseCase>()
    factoryOf(::SetPhoneCallStartedUseCaseImpl)
        .bind<SetPhoneCallStartedUseCase>()
    factoryOf(::SetPhoneCallEndedUseCaseImpl)
        .bind<SetPhoneCallEndedUseCase>()
    factoryOf(::MonitorPhoneCallsUseCaseImpl)
        .bind<MonitorPhoneCallsUseCase>()

    factoryOf(::PhoneCallStateRepositoryImpl)
        .bind<PhoneCallStateRepository>()
    factoryOf(::PhoneCallRepositoryImpl)
        .bind<PhoneCallRepository>()

    factoryOf(::PhoneCallStateDataSourceImpl)
        .bind<PhoneCallStateDataSource>()
    singleOf(::OngoingPhoneCallDataSourceImpl)
        .bind<OngoingPhoneCallDataSource>()
    singleOf(::PhoneCallLogDataSourceImpl)
        .bind<PhoneCallLogDataSource>()
    factoryOf(::ContactNameDataSourceImpl)
        .bind<ContactNameDataSource>()

}