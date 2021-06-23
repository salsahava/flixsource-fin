package com.dicoding.salsahava.flixsource.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId: Int,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "duration")
    var duration: Int,

    @ColumnInfo(name = "score")
    var score: Double,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
) {
    constructor(
        movieId: Int,
        poster: String,
        title: String,
        releaseDate: String,
        description: String
    ) : this(movieId, poster, title, releaseDate, "", -1, 0.1, description)
}