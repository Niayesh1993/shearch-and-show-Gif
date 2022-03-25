package xyz.zohre.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import xyz.zohre.data.discovery.search.SearchGifRepository
import xyz.zohre.data.model.GifData
import xyz.zohre.data.model.Giphy
import xyz.zohre.domain.entities.ApiResult
import xyz.zohre.presentation.Event
import xyz.zohre.presentation.TextData
import xyz.zohre.presentation.parseErrorStringRes
import javax.inject.Inject

class SearchGifViewModel@Inject constructor(val searchGifRepository: SearchGifRepository) : ViewModel() {

    private val _gifs = MutableLiveData<List<GifData>>()
    val gifs: LiveData<List<GifData>> get() = _gifs

    private val _showError = MutableLiveData<Event<TextData>>()
    val showError: LiveData<Event<TextData>> = _showError

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun searchGif(query: String) {

        viewModelScope.launch {

            searchGifRepository(query).collect {
                showResult(it)
            }

        }
    }

    private fun showResult(result: ApiResult<Giphy>) {
        when (result) {
            is ApiResult.Success -> {
                _gifs.value = result.data.data
            }
            ApiResult.Loading -> {
                _loading.value = true

            }
            is ApiResult.Error -> {
                result.exception.let {
                    _showError.value = Event(it.parseErrorStringRes())
                }
            }
        }

    }
}