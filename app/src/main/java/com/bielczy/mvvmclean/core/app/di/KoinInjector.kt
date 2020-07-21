package com.bielczy.mvvmclean.core.app.di

import com.bielczy.mvvmclean.core.app.appModule
import com.bielczy.mvvmclean.core.app.databaseModule
import org.koin.core.module.Module

val koinInjector: List<Module> = listOf(
    networkModule,
    appModule,
    databaseModule
)