package com.zygotecnologia.zygotv.data.remote

import com.zygotecnologia.zygotv.domain.entity.response.GenreListResponse
import com.zygotecnologia.zygotv.domain.entity.response.PopularShowResponse
import com.zygotecnologia.zygotv.domain.entity.response.ShowResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@FlowPreview
@ExperimentalCoroutinesApi
class TmdbDataSource(private val api: TmdbApi): TmdbSource {

    private val popularShowChannel = ConflatedBroadcastChannel<PopularShowResponse>()
    private val genreChannel = ConflatedBroadcastChannel<GenreListResponse>()
    private val  moviesOrSeriesChannel = ConflatedBroadcastChannel<List<ShowResponse>>()
    private val  showChannel = ConflatedBroadcastChannel<ShowResponse>()


    override suspend fun fetchPopularShowsAsync(apiKey: String, region: String): Flow<PopularShowResponse> {
        popularShowChannel.offer(api.fetchPopularShowsAsync(apiKey,region))
        return popularShowChannel.asFlow()
    }

    override suspend fun fetchMoviesOrSeriesAsyncUseCase(apiKey: String, region: String): Flow<List<ShowResponse>> {
        val genres = api.fetchGenresAsync(apiKey,region)
        val shows = api.fetchPopularShowsAsync(apiKey, region)
        moviesOrSeriesChannel.offer(mapperMoviesOrSeries(genres, shows))
        return moviesOrSeriesChannel.asFlow()
    }

    override suspend fun fetchShowAsync(apiKey: String,  id: Int): Flow<ShowResponse> {
        showChannel.offer(api.fetchShowAsync(apiKey, id))
        return showChannel.asFlow()
    }

    private fun mapperMoviesOrSeries(genres: GenreListResponse, shows: PopularShowResponse) = shows.results
            ?.map { show ->
                show.copy(genres = genres.genres?.filter { show.genreIds?.contains(it.id) == true })
            }
            ?: emptyList()
}