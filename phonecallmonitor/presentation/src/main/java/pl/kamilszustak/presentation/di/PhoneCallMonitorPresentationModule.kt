package pl.kamilszustak.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.kamilszustak.presentation.viewmodel.PhoneCallLogViewModel

val phoneCallMonitorPresentationModule: Module = module {

    viewModelOf(::PhoneCallLogViewModel)

}