package com.bielczy.mvvmclean.core.app.roomDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_title")
data class Episodes(@PrimaryKey @ColumnInfo(name = "title") val title: String)