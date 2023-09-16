package pl.kamilszustak.callmonitor.data.phonecallmonitor.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.data.phonecallmonitor.repository.PhoneCallEventRepositoryImpl
import pl.kamilszustak.callmonitor.data.phonecallmonitor.repository.PhoneCallRepositoryImpl
import pl.kamilszustak.callmonitor.data.phonecallmonitor.repository.ServerConfigurationRepositoryImpl
import pl.kamilszustak.callmonitor.data.phonecallmonitor.repository.ServerStatusRepositoryImpl
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallEventRepository
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerConfigurationRepository
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerStatusRepository

val phoneCallMonitorDataModule: Module = module {

    factoryOf(::PhoneCallEventRepositoryImpl)
        .bind<PhoneCallEventRepository>()
    factoryOf(::PhoneCallRepositoryImpl)
        .bind<PhoneCallRepository>()
    factoryOf(::ServerConfigurationRepositoryImpl)
        .bind<ServerConfigurationRepository>()
    factoryOf(::ServerStatusRepositoryImpl)
        .bind<ServerStatusRepository>()

}