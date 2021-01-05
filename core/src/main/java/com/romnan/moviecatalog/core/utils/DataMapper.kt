package com.romnan.moviecatalog.core.utils

import com.romnan.moviecatalog.core.data.source.local.entity.MovieEntity
import com.romnan.moviecatalog.core.data.source.local.entity.TvSeriesEntity
import com.romnan.moviecatalog.core.data.source.remote.response.MovieResponse
import com.romnan.moviecatalog.core.data.source.remote.response.TvSeriesResponse
import com.romnan.moviecatalog.core.domain.model.movie.Movie
import com.romnan.moviecatalog.core.domain.model.tvseries.TvSeries
import com.romnan.moviecatalog.core.presentation.MoviePresentation
import com.romnan.moviecatalog.core.presentation.TvSeriesPresentation

object MovieMapper {
    //    TODO : DELETE THIS
    fun responsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val moviesList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                posterPath = it.posterPath,
                title = it.title,
                releaseDate = it.releaseDate,
                overview = it.overview,
                backdropPath = it.backdropPath,
                revenue = it.revenue,
                budget = it.budget,
                runtime = it.runtime,
                voteAverage = it.voteAverage,
                tagline = it.tagline,
                status = it.status
            )
            moviesList.add(movie)
        }
        return moviesList
    }

    // TODO : DELETE THIS
    fun entitiesToDomains(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                posterPath = it.posterPath,
                title = it.title,
                releaseDate = it.releaseDate,
                overview = it.overview,
                backdropPath = it.backdropPath,
                revenue = it.revenue,
                budget = it.budget,
                runtime = it.runtime,
                voteAverage = it.voteAverage,
                tagline = it.tagline,
                status = it.status,
                isFavorite = it.isFavorite
            )
        }

    fun domainToEntity(input: Movie): MovieEntity = MovieEntity(
        id = input.id,
        posterPath = input.posterPath,
        title = input.title,
        releaseDate = input.releaseDate,
        overview = input.overview,
        backdropPath = input.backdropPath,
        revenue = input.revenue,
        budget = input.budget,
        runtime = input.runtime,
        voteAverage = input.voteAverage,
        tagline = input.tagline,
        status = input.status,
        isFavorite = input.isFavorite
    )

    fun entityToDomain(input: MovieEntity): Movie = Movie(
        id = input.id,
        posterPath = input.posterPath,
        title = input.title,
        releaseDate = input.releaseDate,
        overview = input.overview,
        backdropPath = input.backdropPath,
        revenue = input.revenue,
        budget = input.budget,
        runtime = input.runtime,
        voteAverage = input.voteAverage,
        tagline = input.tagline,
        status = input.status,
        isFavorite = input.isFavorite
    )

    fun domainToPresentation(input: Movie): MoviePresentation = MoviePresentation(
        id = input.id,
        posterPath = input.posterPath,
        title = input.title,
        releaseDate = input.releaseDate,
        overview = input.overview,
        backdropPath = input.backdropPath,
        revenue = input.revenue,
        budget = input.budget,
        runtime = input.runtime,
        voteAverage = input.voteAverage,
        tagline = input.tagline,
        status = input.status,
        isFavorite = input.isFavorite
    )

    fun presentationToDomain(input: MoviePresentation): Movie = Movie(
        id = input.id,
        posterPath = input.posterPath,
        title = input.title,
        releaseDate = input.releaseDate,
        overview = input.overview,
        backdropPath = input.backdropPath,
        revenue = input.revenue,
        budget = input.budget,
        runtime = input.runtime,
        voteAverage = input.voteAverage,
        tagline = input.tagline,
        status = input.status,
        isFavorite = input.isFavorite
    )
}

object TvSeriesMapper {
    //    Delete this
    fun responsesToEntities(input: List<TvSeriesResponse>): List<TvSeriesEntity> {
        val tvSeriesList = ArrayList<TvSeriesEntity>()
        input.map {
            val tvSeries = TvSeriesEntity(
                id = it.id,
                posterPath = it.posterPath,
                name = it.name,
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                tagline = it.tagline,
                numberOfSeasons = it.numberOfSeasons,
                status = it.status,
                type = it.type
            )
            tvSeriesList.add(tvSeries)
        }
        return tvSeriesList
    }

    //    TODO : delete this
    fun entitiesToDomains(input: List<TvSeriesEntity>): List<TvSeries> =
        input.map {
            TvSeries(
                id = it.id,
                posterPath = it.posterPath,
                name = it.name,
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                tagline = it.tagline,
                numberOfSeasons = it.numberOfSeasons,
                status = it.status,
                type = it.type,
                isFavorite = it.isFavorite
            )
        }

    fun domainToEntity(input: TvSeries): TvSeriesEntity = TvSeriesEntity(
        id = input.id,
        posterPath = input.posterPath,
        name = input.name,
        firstAirDate = input.firstAirDate,
        overview = input.overview,
        backdropPath = input.backdropPath,
        voteAverage = input.voteAverage,
        tagline = input.tagline,
        numberOfSeasons = input.numberOfSeasons,
        status = input.status,
        type = input.type,
        isFavorite = input.isFavorite
    )

    fun entityToDomain(input: TvSeriesEntity): TvSeries = TvSeries(
        id = input.id,
        posterPath = input.posterPath,
        name = input.name,
        firstAirDate = input.firstAirDate,
        overview = input.overview,
        backdropPath = input.backdropPath,
        voteAverage = input.voteAverage,
        tagline = input.tagline,
        numberOfSeasons = input.numberOfSeasons,
        status = input.status,
        type = input.type,
        isFavorite = input.isFavorite
    )

    fun domainToPresentation(input: TvSeries): TvSeriesPresentation = TvSeriesPresentation(
        id = input.id,
        posterPath = input.posterPath,
        name = input.name,
        firstAirDate = input.firstAirDate,
        overview = input.overview,
        backdropPath = input.backdropPath,
        voteAverage = input.voteAverage,
        tagline = input.tagline,
        numberOfSeasons = input.numberOfSeasons,
        status = input.status,
        type = input.type,
        isFavorite = input.isFavorite
    )

    fun presentationToDomain(input: TvSeriesPresentation): TvSeries = TvSeries(
        id = input.id,
        posterPath = input.posterPath,
        name = input.name,
        firstAirDate = input.firstAirDate,
        overview = input.overview,
        backdropPath = input.backdropPath,
        voteAverage = input.voteAverage,
        tagline = input.tagline,
        numberOfSeasons = input.numberOfSeasons,
        status = input.status,
        type = input.type,
        isFavorite = input.isFavorite
    )
}