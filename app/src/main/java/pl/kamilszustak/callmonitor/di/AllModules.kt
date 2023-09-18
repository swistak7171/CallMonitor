package pl.kamilszustak.callmonitor.di

import org.koin.core.Koin
import org.koin.core.module.Module

/**
 * A [List] of all [Koin] modules used in the application.
 */
val allModules: List<Module> = listOf(
    loggerModule,
    phoneCallModule,
)