package com.zygotecnologia.zygotv.data.remote

import com.zygotecnologia.zygotv.domain.entity.response.GenreListResponse
import com.zygotecnologia.zygotv.domain.entity.response.PopularShowResponse
import com.zygotecnologia.zygotv.domain.entity.response.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface TmdbApi {

    @GET("$TMDB_API_VERSION/genre/tv/list")
    suspend fun fetchGenresSeriesAsync(): GenreListResponse

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularSeriesAsync(): PopularShowResponse

    @GET("$TMDB_API_VERSION/genre/movie/list")
    suspend fun fetchGenresMoviesAsync(): GenreListResponse

    @GET("$TMDB_API_VERSION/movie/popular")
    suspend fun fetchPopularMoviesAsync(): PopularShowResponse


    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Path("tv_id") id: Int
    ): ShowResponse

    companion object {
        private const val TMDB_API_VERSION = "3"

        const val TMDB_BASE_URL = "https://api.themoviedb.org"
        const val TMDB_API_QUERY = "api_key"
        const val TMDB_API_KEY = "27490b1bf49c0e5ffaa07dfd947e9605"
        const val TMDB_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    }
}