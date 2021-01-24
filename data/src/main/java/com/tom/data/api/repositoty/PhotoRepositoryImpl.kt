package com.tom.data.api.repositoty

import com.tom.data.api.service.PhotoService
import com.tom.domain.models.Photo
import com.tom.domain.repository.PhotoRepository
import com.tom.domain.usecase.base.Error
import com.tom.domain.usecase.base.Result


class PhotoRepositoryImpl(private val photoService: PhotoService) :
    PhotoRepository {
    override suspend fun getPhotos(page: Int): Result<List<Photo>, Error> {
        try {
            val response = photoService.getPhotos(page = page)
            if (response.isSuccessful && response.body() != null) {
                return Result.Success(response.body()!!.map {
                    return@map Photo(
                        it.id,
                        url = it.imageUrls.full,
                        color = it.color,
                        width = it.width,
                        height = it.height
                    )
                })
            } else {
                return Result.Failure(Error.ResponseError)
            }
        } catch (error: Exception) {
            return Result.Failure(Error.NetworkError)
        }
    }
}