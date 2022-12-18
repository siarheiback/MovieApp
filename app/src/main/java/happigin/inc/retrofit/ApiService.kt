package happigin.inc.retrofit

import happigin.inc.domain.models.kinopoisk.KinopoiskResult
import happigin.inc.domain.models.kinopoisk.Releases
import happigin.inc.domain.models.kinopoisk.searhByKey.Search
import happigin.inc.domain.models.kinopoisk.trailer.Trailer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //get movie
    @Headers(
        "Accept: application/json",
        "x-api-key:d31e14a0-297c-414e-9a32-be7d2e5075b3"
    )
    @GET("api/v2.2/films/{id}")
    suspend fun getMovieById(
       @Path("id") id:Long
    ) : Response<KinopoiskResult>


    //get trailer
    @Headers(
        "Accept: application/json",
        "x-api-key:d31e14a0-297c-414e-9a32-be7d2e5075b3"
    )
    @GET("api/v2.2/films/{id}/videos")
    suspend fun getTrailerById(
        @Path("id") id:Long
    ): Response<Trailer>


    //get movie by keyword
    @Headers(
        "Accept: application/json",
        "x-api-key:d31e14a0-297c-414e-9a32-be7d2e5075b3"
    )
    @GET("api/v2.1/films/search-by-keyword")
    suspend fun getMovieByKey(
        @Query("keyword") request:String,
        @Query("page") page:Int
    ): Response<Search>

    //get releases
    @Headers(
        "Accept: application/json",
        "x-api-key:d31e14a0-297c-414e-9a32-be7d2e5075b3"
    )
    @GET("/api/v2.1/films/releases")
    suspend fun getReleases(
        @Query("year") year:Int,
        @Query("month") month:String,
        @Query("page") page:Int
    ): Response<Releases>
}