package xyz.zohre.data.discovery.search

import xyz.zohre.data.model.Giphy
import xyz.zohre.domain.FlowRepository

interface SearchGifRepository: FlowRepository<String, Giphy>