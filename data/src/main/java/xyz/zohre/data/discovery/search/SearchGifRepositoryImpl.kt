package xyz.zohre.data.discovery.search

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import xyz.zohre.data.discovery.GifDataSource
import xyz.zohre.data.model.Giphy
import xyz.zohre.data.model.SearchInput
import xyz.zohre.domain.DefaultDispatcher
import xyz.zohre.domain.IoDispatcher
import xyz.zohre.domain.entities.ApiResult
import xyz.zohre.domain.exeption.RemoteCallException
import javax.inject.Inject

class SearchGifRepositoryImpl@Inject constructor(
    private val remote: GifDataSource,
    @DefaultDispatcher
    override val coroutineDispatcher: CoroutineDispatcher,
    @IoDispatcher
    val ioDispatcher: CoroutineDispatcher
): SearchGifRepository
{
    override suspend fun execute(parameters: SearchInput): Flow<ApiResult<Giphy>> {
        var remoteResponse: Response<Giphy>? = null
        return flow {
            // start async request so we could use network
            val remoteDeferred = CoroutineScope((ioDispatcher)).async {
                remoteResponse = remote.searchGif(parameters.query, parameters.offset)
            }
            // wait for remote call to complete and emit result
            remoteDeferred.await()
            if (remoteResponse?.isSuccessful == false) {
                emit(ApiResult.Error(RemoteCallException(message = remoteResponse!!.errorBody().toString())))
                return@flow
            }
            val remoteVenues = remoteResponse?.body()
            remoteVenues?.let {
                emit(ApiResult.Success(it))
            }
        }
    }


}
