package com.tom.domain.repository

import com.tom.domain.models.Photo
import com.tom.domain.usecase.base.Error
import com.tom.domain.usecase.base.Result

interface PhotoRepository {
    suspend fun getPhotos(page: Int): Result<List<Photo>, Error>
}