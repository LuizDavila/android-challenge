package com.zygotecnologia.zygotv.data.remote

import com.zygotecnologia.zygotv.domain.entity.response.GenreListResponse
import com.zygotecnologia.zygotv.domain.entity.response.MoviesOrSeriesResponse
import com.zygotecnologia.zygotv.domain.entity.response.MovieOrSeriesDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface TmdbApi {

    @GET("$TMDB_API_VERSION/genre/tv/list")
    suspend fun fetchGenresSeriesAsync(): GenreListResponse

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularSeriesAsync(): MoviesOrSeriesResponse

    @GET("$TMDB_API_VERSION/genre/movie/list")
    suspend fun fetchGenresMoviesAsync(): GenreListResponse

    @GET("$TMDB_API_VERSION/movie/popular")
    suspend fun fetchPopularMoviesAsync(): MoviesOrSeriesResponse

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetchSeriesAsync(
        @Path("tv_id") id: Int
    ): MovieOrSeriesDetailResponse

    @GET("$TMDB_API_VERSION/movie/{movie_id}")
    suspend fun fetchMovieAsync(
        @Path("movie_id") id: Int
    ): MovieOrSeriesDetailResponse

    companion object {
        private const val TMDB_API_VERSION = "3"
    }
}