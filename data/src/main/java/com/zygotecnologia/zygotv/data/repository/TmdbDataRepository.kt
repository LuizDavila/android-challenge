package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.data.remote.TmdbSource
import com.zygotecnologia.zygotv.domain.entity.response.MovieOrSeriesDetailResponse
import com.zygotecnologia.zygotv.domain.repository.TmdbRepository
import kotlinx.coroutines.flow.Flow

class TmdbDataRepository(private val source: TmdbSource): TmdbRepository {

    override suspend fun fetchMoviesOrSeriesAsync(isMovie: Boolean) =
        source.fetchMoviesOrSeriesAsync(isMovie)

    override suspend fun fetchShowAsync(isMovie: Boolean,  id: Int): Flow<MovieOrSeriesDetailResponse> =
        source.fetchShowAsync(isMovie, id)

}