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
    private val  moviesOrSeriesChannel = ConflatedBroadcastChannel<Pair<GenreListResponse,List<ShowResponse>>>()
    private val  showChannel = ConflatedBroadcastChannel<ShowResponse>()


    override suspend fun fetchPopularShowsAsync(apiKey: String, region: String): Flow<PopularShowResponse> {
        popularShowChannel.offer(api.fetchPopularSeriesAsync())
        return popularShowChannel.asFlow()
    }

    override suspend fun fetchMoviesOrSeriesAsyncUseCase(isMovie: Boolean): Flow<Pair<GenreListResponse,List<ShowResponse>>> {
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

    override suspend fun fetchShowAsync(apiKey: String,  id: Int): Flow<ShowResponse> {
        showChannel.offer(api.fetchShowAsync(id))
        return showChannel.asFlow()
    }

    private fun mapperMoviesOrSeries(genres: GenreListResponse, shows: PopularShowResponse) = shows.results
            ?.map { show ->
                show.copy(genres = genres.genres?.filter { show.genreIds?.contains(it.id) == true })
            }
            ?: emptyList()
}