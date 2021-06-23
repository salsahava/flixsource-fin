package com.dicoding.salsahava.flixsource.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 4,
    exportSchema = false
)
abstract class FlixDatabase : RoomDatabase() {

    abstract fun flixDao(): FlixDao
}