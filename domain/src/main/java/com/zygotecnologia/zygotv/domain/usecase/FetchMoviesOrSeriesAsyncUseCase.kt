package com.zygotecnologia.zygotv.domain.usecase

import com.zygotecnologia.zygotv.domain.repository.TmdbRepository

class FetchMoviesOrSeriesAsyncUseCase(private val repository: TmdbRepository) {

    suspend operator fun invoke(isMovie: Boolean) =
        repository.fetchMoviesOrSeriesAsync(isMovie)

}