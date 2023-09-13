package pl.kamilszustak.callmonitor.di

import kotlinx.datetime.Clock
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.MainViewModel
import pl.kamilszustak.callmonitor.datasource.ContactNameDataSource
import pl.kamilszustak.callmonitor.datasource.ContactNameDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.datasource.OngoingPhoneCallDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.PhoneCallEventDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallEventDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallLogDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.PhoneCallMetadataDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallMetadataDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.ServerConfigurationDataSource
import pl.kamilszustak.callmonitor.datasource.ServerConfigurationDataSourceImpl
import pl.kamilszustak.callmonitor.repository.PhoneCallEventRepository
import pl.kamilszustak.callmonitor.repository.PhoneCallEventRepositoryImpl
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository
import pl.kamilszustak.callmonitor.repository.PhoneCallRepositoryImpl
import pl.kamilszustak.callmonitor.repository.ServerConfigurationRepository
import pl.kamilszustak.callmonitor.repository.ServerConfigurationRepositoryImpl
import pl.kamilszustak.callmonitor.usecase.GetAllPhoneCallLogEntriesUseCase
import pl.kamilszustak.callmonitor.usecase.GetAllPhoneCallLogEntriesUseCaseImpl
import pl.kamilszustak.callmonitor.usecase.GetOngoingPhoneCallUseCase
import pl.kamilszustak.callmonitor.usecase.GetOngoingPhoneCallUseCaseImpl
import pl.kamilszustak.callmonitor.usecase.GetPhoneCallEventUseCase
import pl.kamilszustak.callmonitor.usecase.GetPhoneCallEventUseCaseImpl
import pl.kamilszustak.callmonitor.usecase.GetServerConfigurationUseCase
import pl.kamilszustak.callmonitor.usecase.GetServerConfigurationUseCaseImpl
import pl.kamilszustak.callmonitor.usecase.MonitorPhoneCallsUseCase
import pl.kamilszustak.callmonitor.usecase.MonitorPhoneCallsUseCaseImpl

val phoneCallModule: Module = module {

    viewModelOf(::MainViewModel)

    factoryOf(::GetPhoneCallEventUseCaseImpl)
        .bind<GetPhoneCallEventUseCase>()
    factoryOf(::MonitorPhoneCallsUseCaseImpl)
        .bind<MonitorPhoneCallsUseCase>()
    factoryOf(::GetAllPhoneCallLogEntriesUseCaseImpl)
        .bind<GetAllPhoneCallLogEntriesUseCase>()
    factoryOf(::GetOngoingPhoneCallUseCaseImpl)
        .bind<GetOngoingPhoneCallUseCase>()
    factoryOf(::GetServerConfigurationUseCaseImpl)
        .bind<GetServerConfigurationUseCase>()

    factoryOf(::PhoneCallEventRepositoryImpl)
        .bind<PhoneCallEventRepository>()
    factoryOf(::PhoneCallRepositoryImpl)
        .bind<PhoneCallRepository>()
    factoryOf(::ServerConfigurationRepositoryImpl)
        .bind<ServerConfigurationRepository>()

    factoryOf(::PhoneCallEventDataSourceImpl)
        .bind<PhoneCallEventDataSource>()
    singleOf(::OngoingPhoneCallDataSourceImpl)
        .bind<OngoingPhoneCallDataSource>()
    singleOf(::PhoneCallLogDataSourceImpl)
        .bind<PhoneCallLogDataSource>()
    factoryOf(::ContactNameDataSourceImpl)
        .bind<ContactNameDataSource>()
    singleOf(::PhoneCallMetadataDataSourceImpl)
        .bind<PhoneCallMetadataDataSource>()
    factoryOf(::ServerConfigurationDataSourceImpl)
        .bind<ServerConfigurationDataSource>()

    factory<Clock> {
        Clock.System
    }

}