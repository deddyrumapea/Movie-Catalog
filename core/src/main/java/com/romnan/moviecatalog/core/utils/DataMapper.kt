package com.romnan.moviecatalog.core.utils

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
        id = if (input.id != null) input.id else 0,
        posterPath = input.posterPath.toString(),
        title = input.title.toString(),
        releaseDate = input.releaseDate.toString(),
        overview = input.overview.toString(),
        backdropPath = input.backdropPath.toString(),
        revenue = if (input.revenue != null) input.revenue else 0,
        budget = if (input.budget != null) input.budget else 0,
        runtime = if (input.runtime != null) input.runtime else 0,
        voteAverage = if (input.voteAverage != null) input.voteAverage else 0.0,
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
        id = if (input.id != null) input.id else 0,
        posterPath = input.posterPath.toString(),
        name = input.name.toString(),
        firstAirDate = input.firstAirDate.toString(),
        overview = input.overview.toString(),
        backdropPath = input.backdropPath.toString(),
        voteAverage = if (input.voteAverage != null) input.voteAverage else 0.0,
        tagline = input.tagline.toString(),
        numberOfSeasons = if (input.numberOfSeasons != null) input.numberOfSeasons else 0,
        status = input.status.toString(),
        type = input.type.toString(),
    )
}