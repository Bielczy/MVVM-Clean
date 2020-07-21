package com.bielczy.mvvmclean.core.app

import androidx.room.Room
import com.bielczy.mvvmclean.core.app.roomDataBase.EpisodesDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(androidApplication(), EpisodesDB::class.java, "EpisodesDB")
            .build()
    }
}