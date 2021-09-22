package com.zygotecnologia.zygotv.domain.usecase

import com.zygotecnologia.zygotv.domain.repository.TmdbRepository

class FetchMoviesOrSeriesDetailAsyncUseCase(private val repository: TmdbRepository) {

    suspend operator fun invoke(isMovie: Boolean, id: Int) =
        repository.fetchShowAsync(isMovie, id)

}