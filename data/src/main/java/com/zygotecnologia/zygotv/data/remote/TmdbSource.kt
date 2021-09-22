package com.zygotecnologia.zygotv.data.remote

import com.zygotecnologia.zygotv.domain.entity.response.GenreListResponse
import com.zygotecnologia.zygotv.domain.entity.response.MovieOrSeriesDetailResponse
import kotlinx.coroutines.flow.Flow

interface TmdbSource {

    suspend fun fetchMoviesOrSeriesAsync(isMovie: Boolean): Flow<Pair<GenreListResponse,List<MovieOrSeriesDetailResponse>>>

    suspend fun fetchShowAsync(isMovie: Boolean, id: Int): Flow<MovieOrSeriesDetailResponse>

}