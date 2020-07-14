package com.bielczy.mvvmclean.core.app.di

import org.koin.core.module.Module

val koinInjector: List<Module> = listOf(
    networkModule
)