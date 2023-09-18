package pl.kamilszustak.callmonitor.di

import org.koin.core.Koin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kamilszustak.callmonitor.logger.Logger
import pl.kamilszustak.callmonitor.logger.LoggerImpl

/**
 * A [Koin] module providing a [Logger] implementation.
 */
val loggerModule: Module = module {
    factoryOf(::LoggerImpl)
        .bind<Logger>()
}