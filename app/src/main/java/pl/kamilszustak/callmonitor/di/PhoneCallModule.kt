package pl.kamilszustak.callmonitor.di

import kotlinx.datetime.Clock
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.MainViewModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ContactNameDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallEventDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallMetadataDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerConfigurationDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerStatusDataSource
import pl.kamilszustak.callmonitor.datasource.ContactNameDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.OngoingPhoneCallDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.PhoneCallEventDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.PhoneCallLogDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.PhoneCallMetadataDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.ServerConfigurationDataSourceImpl
import pl.kamilszustak.callmonitor.datasource.ServerStatusDataSourceImpl

val phoneCallModule: Module = module {

    viewModelOf(::MainViewModel)

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