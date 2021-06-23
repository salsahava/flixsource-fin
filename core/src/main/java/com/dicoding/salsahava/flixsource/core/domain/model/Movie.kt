package com.dicoding.salsahava.flixsource.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val poster: String,
    val title: String,
    val releaseDate: String,
    val genres: String,
    val duration: Int,
    val score: Double,
    val description: String,
    val favorited: Boolean
) : Parcelable {
    constructor(
        movieId: Int,
        poster: String,
        title: String,
        releaseDate: String,
        description: String
    ) : this(movieId, poster, title, releaseDate, "", -1, 0.1, description, false)
}
