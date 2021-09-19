package com.zygotecnologia.zygotv.domain.usecase

import com.zygotecnologia.zygotv.domain.repository.TmdbRepository

class fetchShowAsyncUseCase(private val repository: TmdbRepository) {

    suspend operator fun invoke(apiKey: String, id: Int) =
        repository.fetchShowAsync(apiKey, id)

}