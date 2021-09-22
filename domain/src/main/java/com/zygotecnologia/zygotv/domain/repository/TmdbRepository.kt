package com.zygotecnologia.zygotv.domain.repository

import com.zygotecnologia.zygotv.domain.entity.response.GenreListResponse
import com.zygotecnologia.zygotv.domain.entity.response.MovieOrSeriesDetailResponse
import kotlinx.coroutines.flow.Flow

interface TmdbRepository {

    suspend fun fetchMoviesOrSeriesAsync(isMovie: Boolean): Flow<Pair<GenreListResponse,List<MovieOrSeriesDetailResponse>>>

    suspend fun fetchShowAsync(isMovie: Boolean,  id: Int): Flow<MovieOrSeriesDetailResponse>

}