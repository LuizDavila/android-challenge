package com.zygotecnologia.zygotv.data.remote

import android.content.Context
import com.zygotecnologia.zygotv.data.util.ImageHelper
import com.zygotecnologia.zygotv.domain.entity.response.GenreListResponse
import com.zygotecnologia.zygotv.domain.entity.response.MovieOrSeriesDetailResponse
import com.zygotecnologia.zygotv.domain.entity.response.MoviesOrSeriesResponse
import com.zygotecnologia.zygotv.domain.entity.response.SeasonResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@FlowPreview
@ExperimentalCoroutinesApi
class TmdbDataSource(private val api: TmdbApi, val context: Context): TmdbSource {

    private val  moviesOrSeriesChannel = ConflatedBroadcastChannel<Pair<GenreListResponse,List<MovieOrSeriesDetailResponse>>>()
    private val  movieOrSeriesDetailChannel = ConflatedBroadcastChannel<MovieOrSeriesDetailResponse>()

    override suspend fun fetchMoviesOrSeriesAsync(isMovie: Boolean): Flow<Pair<GenreListResponse,List<MovieOrSeriesDetailResponse>>> {
        if (isMovie) {
            val genres = api.fetchGenresMoviesAsync()
            val popular = api.fetchPopularMoviesAsync()
            moviesOrSeriesChannel.offer(Pair(genres,mapperMoviesOrSeries(genres, popular)))
        } else {
            val genres = api.fetchGenresSeriesAsync()
            val popular = api.fetchPopularSeriesAsync()
            moviesOrSeriesChannel.offer(Pair(genres,mapperMoviesOrSeries(genres, popular)))
        }

        return moviesOrSeriesChannel.asFlow()
    }

    override suspend fun fetchShowAsync(isMovie: Boolean,  id: Int): Flow<MovieOrSeriesDetailResponse> {
        if (isMovie) {
            movieOrSeriesDetailChannel.offer(mapperDetailMovie(api.fetchMovieAsync(id)))
        } else {
            movieOrSeriesDetailChannel.offer(mapperDetailSeries(api.fetchSeriesAsync(id)))
        }
        return movieOrSeriesDetailChannel.asFlow()
    }

    private fun mapperMoviesOrSeries(genres: GenreListResponse, shows: MoviesOrSeriesResponse) = shows.results
            ?.map { show ->
                show.copy(genres = genres.genres?.filter { show.genreIds?.contains(it.id) == true },
                posterPath = ImageHelper.buildPosterUrl(show.posterPath, context),
                backdropPath = ImageHelper.buildBackdropUrl(show.backdropPath, context))
            }
            ?: emptyList()

    private fun mapperDetailSeries(fetchMovieAsync: MovieOrSeriesDetailResponse) =
        fetchMovieAsync.copy(
            posterPath = ImageHelper.buildPosterUrl(fetchMovieAsync.posterPath, context),
            backdropPath = ImageHelper.buildBackdropUrl(fetchMovieAsync.backdropPath, context),
            season = fetchMovieAsync.season?.map {
                it.copy(
                    posterPath = ImageHelper.buildPosterUrl(it.posterPath, context)
                )
            }
        )

    private fun mapperDetailMovie(fetchMovieAsync: MovieOrSeriesDetailResponse) =
        fetchMovieAsync.copy(
            name = fetchMovieAsync.title,
            posterPath = ImageHelper.buildPosterUrl(fetchMovieAsync.posterPath, context),
            backdropPath = ImageHelper.buildBackdropUrl(fetchMovieAsync.backdropPath, context),
            season = listOf(SeasonResponse(0,fetchMovieAsync.title,
                ImageHelper.buildPosterUrl(fetchMovieAsync.posterPath, context),fetchMovieAsync.overview))
        )
}