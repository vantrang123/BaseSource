package com.tom.template.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.tom.domain.models.Photo
import com.tom.domain.usecase.GetPhotosUseCase
import com.tom.domain.usecase.base.Error
import com.tom.domain.usecase.base.Result
import com.tom.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class PhotoListViewModel(private val getPhotosUseCase: GetPhotosUseCase) : BaseViewModel() {
    override val receiveChannel: ReceiveChannel<Result<Any, Error>>
        get() = getPhotosUseCase.receiveChannel

    private val _items = MutableLiveData<List<Photo>>().apply { value = emptyList() }
    val items: LiveData<List<Photo>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    var page: Int = 1

    fun requestItems() {
        getPhotosUseCase(page)
    }

    fun refresh() {
        _items.value = emptyList()
        page = 1
        requestItems()
    }

    override fun resolve(value: Result<Any, Error>) {
        value.handleResult(::handleSuccess, ::handleFailure, ::handleState)
    }

    fun handleSuccess(data: Any) {
        val oldItems = _items.value!!
        val items = oldItems + (data as List<Photo>)
        _items.postValue(items)
        page++
    }

    fun handleFailure(error: Error) {

    }

    fun handleState(state: Result.State) {
        _dataLoading.postValue(state is Result.State.Loading)
    }
}