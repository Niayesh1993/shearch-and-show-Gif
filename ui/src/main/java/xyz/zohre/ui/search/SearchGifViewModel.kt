package xyz.zohre.ui.search

import androidx.lifecycle.ViewModel
import xyz.zohre.data.discovery.search.SearchGifRepository
import javax.inject.Inject

class SearchGifViewModel@Inject constructor(val searchGifRepository: SearchGifRepository) : ViewModel() {
}