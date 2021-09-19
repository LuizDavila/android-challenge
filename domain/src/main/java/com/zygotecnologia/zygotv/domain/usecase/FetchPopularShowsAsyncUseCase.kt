package com.zygotecnologia.zygotv.domain.usecase

import com.zygotecnologia.zygotv.domain.repository.TmdbRepository

class fetchPopularShowsAsyncUseCase(private val repository: TmdbRepository) {

    suspend operator fun invoke(apiKey: String, region: String) =
        repository.fetchPopularShowsAsync(apiKey, region)
}