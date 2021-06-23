package com.dicoding.salsahava.flixsource.core.utils.dummy

import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieDetail
import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieGenresItem
import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieItem

object MovieData {

    private const val posterDummy =
        "https://image.tmdb.org/t/p/original/wwemzKWzjKYJFfCeiB57q3r4Bcm.png"
    private const val posterPathDummy = "/wwemzKWzjKYJFfCeiB57q3r4Bcm.png"
    private val genresItemDummy = arrayListOf(
        MovieGenresItem("Adventure", 0),
        MovieGenresItem("Action", 1),
        MovieGenresItem("Science Fiction", 2)
    )

    fun generateDummyMovies(): List<com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity> {
        val movies =
            ArrayList<com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity>()

        movies.add(
            com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity(
                0,
                posterDummy,
                "Mortal Kombat",
                "2021-04-07",
                "Action, Fantasy, Adventure, Science Fiction",
                110,
                7.7,
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )

        movies.add(
            com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity(
                1,
                posterDummy,
                "Avengers: Infinity War",
                "2018-04-25",
                "Adventure, Action, Science Fiction",
                149,
                8.3,
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."
            )
        )

        movies.add(
            com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity(
                2,
                posterDummy,
                "Spider-Man: Into the Spider-Verse",
                "2018-12-06",
                "Action, Adventure, Animation, Science Fiction, Comedy",
                117,
                8.4,
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension."
            )
        )

        movies.add(
            com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity(
                3,
                posterDummy,
                "Glass",
                "2019-01-16",
                "Thriller, Drama, Science Fiction",
                129,
                6.7,
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men."
            )
        )

        movies.add(
            com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity(
                4,
                posterDummy,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "2018-11-14",
                "Adventure, Fantasy, Drama",
                134,
                6.9,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world."
            )
        )

        return movies
    }

    fun generateRemoteDummyMovies(): List<MovieItem> {
        val movies = ArrayList<MovieItem>()

        movies.add(
            MovieItem(
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07",
                0,
                "Mortal Kombat",
                posterPathDummy
            )
        )

        movies.add(
            MovieItem(
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "2018-04-25",
                1,
                "Avengers: Infinity War",
                posterPathDummy
            )
        )

        movies.add(
            MovieItem(
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "2018-12-06",
                2,
                "Spider-Man: Into the Spider-Verse",
                posterPathDummy
            )
        )

        movies.add(
            MovieItem(
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "2019-01-16",
                3,
                "Glass",
                posterPathDummy
            )
        )

        movies.add(
            MovieItem(
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "2018-11-14",
                4,
                "Fantastic Beasts: The Crimes of Grindelwald",
                posterPathDummy
            )
        )

        return movies
    }

    fun generateRemoteDummyMovieDetail(): MovieDetail {
        return MovieDetail(
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            "2018-04-25",
            genresItemDummy,
            8.3,
            149,
            0,
            "Avengers: Infinity War",
            posterPathDummy
        )
    }

    fun generateDummyMovieDetail(): com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity {
        return com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity(
            0,
            posterDummy,
            "Mortal Kombat",
            "2021-04-07",
            "Action, Fantasy, Adventure, Science Fiction",
            110,
            7.7,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
        )
    }
}