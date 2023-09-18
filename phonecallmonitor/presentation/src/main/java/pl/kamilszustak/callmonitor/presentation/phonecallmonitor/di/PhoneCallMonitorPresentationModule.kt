package pl.kamilszustak.callmonitor.presentation.phonecallmonitor.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.Koin
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.viewmodel.PhoneCallLogViewModel

/**
 * A [Koin] module containing all dependencies for the presentation layer.
 */
val phoneCallMonitorPresentationModule: Module = module {
    viewModelOf(::PhoneCallLogViewModel)
}