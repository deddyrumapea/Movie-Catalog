package com.romnan.moviecatalog.core.util

import com.romnan.moviecatalog.core.data.source.local.entity.MovieEntity
import com.romnan.moviecatalog.core.data.source.local.entity.TvSeriesEntity
import com.romnan.moviecatalog.core.data.source.remote.response.MovieResponse
import com.romnan.moviecatalog.core.data.source.remote.response.TvSeriesResponse
import com.romnan.moviecatalog.core.domain.model.movie.Movie
import com.romnan.moviecatalog.core.domain.model.tvseries.TvSeries
import com.romnan.moviecatalog.core.presentation.movie.MovieBrief
import com.romnan.moviecatalog.core.presentation.movie.MovieDetail
import com.romnan.moviecatalog.core.presentation.tvseries.TvSeriesBrief
import com.romnan.moviecatalog.core.presentation.tvseries.TvSeriesDetail

object MovieMapper {
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

    fun domainToDetail(input: Movie): MovieDetail = MovieDetail(
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

    fun detailToDomain(input: MovieDetail): Movie = Movie(
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

    fun domainToBrief(input: Movie): MovieBrief = MovieBrief(
        id = input.id,
        posterPath = input.posterPath,
        title = input.title,
        releaseDate = input.releaseDate,
        overview = input.overview
    )

    fun responseToEntity(input: MovieResponse): MovieEntity = MovieEntity(
        id = input.id ?: 0,
        posterPath = input.posterPath.toString(),
        title = input.title.toString(),
        releaseDate = input.releaseDate.toString(),
        overview = input.overview.toString(),
        backdropPath = input.backdropPath.toString(),
        revenue = input.revenue ?: 0,
        budget = input.budget ?: 0,
        runtime = input.runtime ?: 0,
        voteAverage = input.voteAverage ?: 0.0,
        tagline = input.tagline.toString(),
        status = input.status.toString(),
    )
}

object TvSeriesMapper {

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

    fun domainToDetail(input: TvSeries): TvSeriesDetail = TvSeriesDetail(
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

    fun detailToDomain(input: TvSeriesDetail): TvSeries = TvSeries(
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

    fun domainToBrief(input: TvSeries): TvSeriesBrief = TvSeriesBrief(
        id = input.id,
        posterPath = input.posterPath,
        name = input.name,
        firstAirDate = input.firstAirDate,
        overview = input.overview
    )

    fun responseToEntity(input: TvSeriesResponse): TvSeriesEntity = TvSeriesEntity(
        id = input.id ?: 0,
        posterPath = input.posterPath.toString(),
        name = input.name.toString(),
        firstAirDate = input.firstAirDate.toString(),
        overview = input.overview.toString(),
        backdropPath = input.backdropPath.toString(),
        voteAverage = input.voteAverage ?: 0.0,
        tagline = input.tagline.toString(),
        numberOfSeasons = input.numberOfSeasons ?: 0,
        status = input.status.toString(),
        type = input.type.toString(),
    )
}