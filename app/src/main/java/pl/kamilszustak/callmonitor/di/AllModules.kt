package pl.kamilszustak.callmonitor.di

import org.koin.core.module.Module

val allModules: List<Module> = listOf(
    loggerModule,
    phoneCallModule,
)