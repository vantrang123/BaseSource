package com.tom.template.di

import com.tom.data.api.repositoty.PhotoRepositoryImpl
import com.tom.data.api.service.PhotoService
import com.tom.domain.repository.PhotoRepository
import com.tom.domain.usecase.GetPhotosUseCase
import com.tom.template.fragment.PhotoListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModules = module {
    single(named("remote")) {
        PhotoRepositoryImpl(get(named(API))) as PhotoRepository
    }
}

val useCaseModules = module {
    factory(named(GET_NEWS_USECASE)) {
        GetPhotosUseCase(photoRepository = get(named("remote")))
    }
}

val networkModules = module {
    single(named(OKHTTP_INSTANCE)) { createOkHttpClient() }
    single(named(API)) { createWebService<PhotoService>(get(named(OKHTTP_INSTANCE)), BASE_URL) }
}

val viewModels = module {
    viewModel {
        PhotoListViewModel(get(named(GET_NEWS_USECASE)))
    }
}
private const val BASE_URL = "https://api.unsplash.com/"
private const val OKHTTP_INSTANCE = "OkHttp"
private const val API = "Api"
private const val GET_NEWS_USECASE = "getNewsUseCase"