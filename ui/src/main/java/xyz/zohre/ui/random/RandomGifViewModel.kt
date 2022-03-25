package xyz.zohre.ui.random

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import xyz.zohre.data.discovery.random.RandomGifRepository
import xyz.zohre.data.model.RandomGiphy
import xyz.zohre.domain.entities.ApiResult
import xyz.zohre.presentation.Event
import xyz.zohre.presentation.TextData
import xyz.zohre.presentation.parseErrorStringRes
import javax.inject.Inject

class RandomGifViewModel@Inject constructor(val randomGifRepository: RandomGifRepository) : ViewModel() {

    private val _randomGif = MutableLiveData<RandomGiphy>()
    val randomGif: LiveData<RandomGiphy> get() = _randomGif

    private val _showError = MutableLiveData<Event<TextData>>()
    val showError: LiveData<Event<TextData>> = _showError

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun searchRandomGif() {

        viewModelScope.launch {

            randomGifRepository(Any()).collect {
                showResult(it)
            }
        }
    }

    private fun showResult(result: ApiResult<RandomGiphy>) {
        when (result) {
            is ApiResult.Success -> {
                _randomGif.value = result.data
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