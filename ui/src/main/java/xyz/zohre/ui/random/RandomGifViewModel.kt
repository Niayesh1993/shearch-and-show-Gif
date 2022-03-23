package xyz.zohre.ui.random

import androidx.lifecycle.ViewModel
import xyz.zohre.data.discovery.random.RandomGifRepository
import javax.inject.Inject

class RandomGifViewModel@Inject constructor(val randomGifRepository: RandomGifRepository) : ViewModel() {
}