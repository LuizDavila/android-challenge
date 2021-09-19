package com.zygotecnologia.zygotv.data.remote

import com.zygotecnologia.zygotv.domain.entity.response.PopularShowResponse
import com.zygotecnologia.zygotv.domain.entity.response.ShowResponse
import kotlinx.coroutines.flow.Flow

interface TmdbSource {

    suspend fun fetchPopularShowsAsync(apiKey: String, region: String): Flow<PopularShowResponse>

    suspend fun fetchMoviesOrSeriesAsyncUseCase(apiKey: String, region: String): Flow<List<ShowResponse>>

    suspend fun fetchShowAsync(apiKey: String, id: Int): Flow<ShowResponse>

}