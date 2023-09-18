package pl.kamilszustak.callmonitor.di

import org.koin.core.Koin
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.data.phonecallmonitor.di.phoneCallMonitorDataModule
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.di.phoneCallMonitorDataSourceModule
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.di.phoneCallMonitorDomainModule
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.di.phoneCallMonitorPresentationModule

/**
 * A [Koin] module aggregating all modules related to the phone call monitoring feature.
 */
val phoneCallModule: Module = module {
    includes(
        phoneCallMonitorPresentationModule,
        phoneCallMonitorDomainModule,
        phoneCallMonitorDataModule,
        phoneCallMonitorDataSourceModule,
    )
}