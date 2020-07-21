package com.bielczy.mvvmclean.core.app.roomDataBase

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface EpisodesDao {

    @Query("SELECT * from episode_title ORDER BY title ASC")
    fun getAlphabetizedWords(): LiveData<List<Episodes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(title: Episodes)

    @Query("DELETE FROM episode_title")
    fun deleteAll()
}