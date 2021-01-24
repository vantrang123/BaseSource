package com.tom.domain.usecase

import com.tom.domain.repository.PhotoRepository
import com.tom.domain.usecase.base.BaseUseCase
import com.tom.domain.usecase.base.Result

class GetPhotosUseCase(private val photoRepository: PhotoRepository) : BaseUseCase<Int>() {
    override suspend fun run(params: Int) {
        // Started loading
        resultChannel.send(Result.State.Loading)

        resultChannel.send(photoRepository.getPhotos(params))

        resultChannel.send(Result.State.Loaded)
    }
}