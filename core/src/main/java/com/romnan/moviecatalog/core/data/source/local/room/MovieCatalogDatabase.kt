package com.romnan.moviecatalog.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.romnan.moviecatalog.core.data.source.local.entity.MovieEntity
import com.romnan.moviecatalog.core.data.source.local.entity.TvSeriesEntity

@Database(
    entities = [MovieEntity::class, TvSeriesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieCatalogDatabase : RoomDatabase() {
    abstract fun movieCatalogDao(): MovieCatalogDao
}