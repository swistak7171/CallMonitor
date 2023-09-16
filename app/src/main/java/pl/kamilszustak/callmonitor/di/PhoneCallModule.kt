package pl.kamilszustak.callmonitor.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.MainViewModel
import pl.kamilszustak.callmonitor.logger.Logger
import pl.kamilszustak.callmonitor.logger.LoggerImpl

val phoneCallModule: Module = module {

    viewModelOf(::MainViewModel)

    factoryOf(::LoggerImpl)
        .bind<Logger>()

}