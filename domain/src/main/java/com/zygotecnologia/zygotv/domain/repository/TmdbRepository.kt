package com.zygotecnologia.zygotv.domain.repository

import com.zygotecnologia.zygotv.domain.entity.response.GenreListResponse
import com.zygotecnologia.zygotv.domain.entity.response.PopularShowResponse
import com.zygotecnologia.zygotv.domain.entity.response.ShowResponse
import kotlinx.coroutines.flow.Flow

interface TmdbRepository {

    suspend fun fetchPopularShowsAsync(apiKey: String, region: String): Flow<PopularShowResponse>

    suspend fun fetchMoviesOrSeriesAsyncUseCase(isMovie: Boolean): Flow<Pair<GenreListResponse,List<ShowResponse>>>

    suspend fun fetchShowAsync(apiKey: String,  id: Int): Flow<ShowResponse>

}