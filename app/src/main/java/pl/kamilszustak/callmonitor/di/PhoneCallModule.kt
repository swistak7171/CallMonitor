package pl.kamilszustak.callmonitor.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.logger.Logger
import pl.kamilszustak.callmonitor.logger.LoggerImpl

val phoneCallModule: Module = module {

    factoryOf(::LoggerImpl)
        .bind<Logger>()

}