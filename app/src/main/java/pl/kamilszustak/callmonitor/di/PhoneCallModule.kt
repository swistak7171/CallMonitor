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
import pl.kamilszustak.callmonitor.datasource.ServerStatusDataSource
import pl.kamilszustak.callmonitor.datasource.ServerStatusDataSourceImpl
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallEventRepository
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerConfigurationRepository
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerStatusRepository
import pl.kamilszustak.callmonitor.repository.PhoneCallEventRepositoryImpl
import pl.kamilszustak.callmonitor.repository.PhoneCallRepositoryImpl
import pl.kamilszustak.callmonitor.repository.ServerConfigurationRepositoryImpl
import pl.kamilszustak.callmonitor.repository.ServerStatusRepositoryImpl

val phoneCallModule: Module = module {

    viewModelOf(::MainViewModel)

    factoryOf(::PhoneCallEventRepositoryImpl)
        .bind<PhoneCallEventRepository>()
    factoryOf(::PhoneCallRepositoryImpl)
        .bind<PhoneCallRepository>()
    factoryOf(::ServerConfigurationRepositoryImpl)
        .bind<ServerConfigurationRepository>()
    factoryOf(::ServerStatusRepositoryImpl)
        .bind<ServerStatusRepository>()

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
    singleOf(::ServerStatusDataSourceImpl)
        .bind<ServerStatusDataSource>()

    factory<Clock> {
        Clock.System
    }

}