package com.bielczy.mvvmclean.core.app.roomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Episodes::class], version = 1)
abstract class EpisodesDB : RoomDatabase() {

    abstract fun episodesDao(): EpisodesDao

    companion object {
        @Volatile
        private var INSTANCE: EpisodesDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): EpisodesDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EpisodesDB::class.java,
                    "episodes_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .addCallback(EpisodesDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class EpisodesDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.episodesDao())
                    }
                }
            }
        }

        fun populateDatabase(episodesDao: EpisodesDao) {

            var episode = Episodes("Hello")
            episodesDao.insert(episode)

        }
    }

}
