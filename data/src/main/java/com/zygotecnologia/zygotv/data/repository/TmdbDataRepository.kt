package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.data.remote.TmdbSource
import com.zygotecnologia.zygotv.domain.entity.response.ShowResponse
import com.zygotecnologia.zygotv.domain.repository.TmdbRepository
import kotlinx.coroutines.flow.Flow

class TmdbDataRepository(private val source: TmdbSource): TmdbRepository {

    override suspend fun fetchPopularShowsAsync(apiKey: String, region: String) =
        source.fetchPopularShowsAsync(apiKey, region)

    override suspend fun fetchMoviesOrSeriesAsyncUseCase(isMovie: Boolean) =
        source.fetchMoviesOrSeriesAsyncUseCase(isMovie)

    override suspend fun fetchShowAsync(apiKey: String,  id: Int): Flow<ShowResponse> =
        source.fetchShowAsync(apiKey, id)

}