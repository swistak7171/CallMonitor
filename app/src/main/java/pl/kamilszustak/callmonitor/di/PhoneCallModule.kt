package pl.kamilszustak.callmonitor.di

import org.koin.core.module.Module
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.data.phonecallmonitor.di.phoneCallMonitorDataModule
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.di.phoneCallMonitorDataSourceModule
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.di.phoneCallMonitorDomainModule
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.di.phoneCallMonitorPresentationModule

val phoneCallModule: Module = module {
    includes(
        phoneCallMonitorPresentationModule,
        phoneCallMonitorDomainModule,
        phoneCallMonitorDataModule,
        phoneCallMonitorDataSourceModule,
    )
}