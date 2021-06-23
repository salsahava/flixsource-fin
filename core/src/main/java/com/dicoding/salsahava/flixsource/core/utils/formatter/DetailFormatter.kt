package com.dicoding.salsahava.flixsource.core.utils.formatter

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DetailFormatter {
    fun releaseDateFormatter(dateString: String): String {
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat: DateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())

        val date: Date = inputFormat.parse(dateString) as Date

        return outputFormat.format(date)
    }

    fun genresFormatter(genreList: ArrayList<String>): String =
        genreList.joinToString(", ")

    fun durationFormatter(duration: Int): String {
        return if (duration < 60) "${duration}m"
        else {
            val hour = (duration / 60).toString()
            val minute = (duration % 60).toString()

            "${hour}h ${minute}m"
        }
    }

    fun posterFormatter(posterPath: String): String {
        return "https://image.tmdb.org/t/p/original/${posterPath}"
    }
}