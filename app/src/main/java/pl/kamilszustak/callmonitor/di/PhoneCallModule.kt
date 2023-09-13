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
import pl.kamilszustak.callmonitor.repository.PhoneCallEventRepository
import pl.kamilszustak.callmonitor.repository.PhoneCallEventRepositoryImpl
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository
import pl.kamilszustak.callmonitor.repository.PhoneCallRepositoryImpl
import pl.kamilszustak.callmonitor.usecase.GetAllPhoneCallLogEntriesUC
import pl.kamilszustak.callmonitor.usecase.GetAllPhoneCallLogEntriesUCImpl
import pl.kamilszustak.callmonitor.usecase.GetOngoingPhoneCallUseCase
import pl.kamilszustak.callmonitor.usecase.GetOngoingPhoneCallUseCaseImpl
import pl.kamilszustak.callmonitor.usecase.GetPhoneCallEventUseCase
import pl.kamilszustak.callmonitor.usecase.GetPhoneCallEventUseCaseImpl
import pl.kamilszustak.callmonitor.usecase.MonitorPhoneCallsUseCase
import pl.kamilszustak.callmonitor.usecase.MonitorPhoneCallsUseCaseImpl

val phoneCallModule: Module = module {

    viewModelOf(::MainViewModel)

    factoryOf(::GetPhoneCallEventUseCaseImpl)
        .bind<GetPhoneCallEventUseCase>()
    factoryOf(::MonitorPhoneCallsUseCaseImpl)
        .bind<MonitorPhoneCallsUseCase>()
    factoryOf(::GetAllPhoneCallLogEntriesUCImpl)
        .bind<GetAllPhoneCallLogEntriesUC>()
    factoryOf(::GetOngoingPhoneCallUseCaseImpl)
        .bind<GetOngoingPhoneCallUseCase>()

    factoryOf(::PhoneCallEventRepositoryImpl)
        .bind<PhoneCallEventRepository>()
    factoryOf(::PhoneCallRepositoryImpl)
        .bind<PhoneCallRepository>()

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

    factory<Clock> {
        Clock.System
    }

}