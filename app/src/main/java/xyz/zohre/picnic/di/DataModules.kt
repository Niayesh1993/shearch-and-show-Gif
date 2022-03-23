package xyz.zohre.picnic.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.zohre.data.discovery.random.RandomGifRepository
import xyz.zohre.data.discovery.random.RandomGifRepositoryImpl
import xyz.zohre.data.discovery.search.SearchGifRepository
import xyz.zohre.data.discovery.search.SearchGifRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
class DataModules {
    @Provides
    fun provideRandomGifRepository(randomGifRepositoryImpl: RandomGifRepositoryImpl): RandomGifRepository {
        return randomGifRepositoryImpl
    }

    @Provides
    fun provideSearchGifRepository(searchGifRepositoryImpl: SearchGifRepositoryImpl): SearchGifRepository {
        return searchGifRepositoryImpl
    }
}