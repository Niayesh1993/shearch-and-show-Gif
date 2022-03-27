package xyz.zohre.data.discovery

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.zohre.data.model.Giphy
import xyz.zohre.data.model.RandomGiphy

interface GifDataSource {

    companion object {
        private const val API_KEY = "ZY2avPUSjnFFy0FcjbTLpKNHfAdJD9Vt"
    }

    @GET("/v1/gifs/random")
    suspend fun randomGif(
        @Query("api_key") api_key: String = API_KEY
    ): Response<RandomGiphy>

    @GET("/v1/gifs/search")
    suspend fun searchGif(
        @Query("q") quest: String,
        @Query("offset") page: Int,
        @Query("api_key") api_key: String = API_KEY
    ): Response<Giphy>



}