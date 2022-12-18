package happigin.inc.data.network

import happigin.inc.data.network.models.kinopoisk.releases.KinopoiskResult
import happigin.inc.data.network.models.kinopoisk.releases.Releases
import happigin.inc.data.network.models.kinopoisk.searhByKey.Search
import happigin.inc.data.network.models.kinopoisk.trailer.Trailer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Success result code 200
 *
 * Exceptions codes:
 * 401 null or invalid token
 * 402 request limit exceeded
 * 403 not found
 * 429 too many requests
 */
interface ApiService {

    /**
     * @return movie info on success [KinopoiskResult]
     * @param[id] movie id
     */
    @GET("api/v2.2/films/{id}")
    suspend fun getMovieById(
       @Path("id") id:Long
    ) : Response<KinopoiskResult>

    /**
     * @return [Trailer] on Success
     * @param[id] movie id
     */
    @GET("api/v2.2/films/{id}/videos")
    suspend fun getTrailerById(
        @Path("id") id:Long
    ): Response<Trailer>


    /**
     * @return list of Film on Success [Search]
     * @param[request] contains key words for search, for example movie name "Matrix"
     * @param[page] page number
     */
    @GET("api/v2.1/films/search-by-keyword")
    suspend fun getMovieByKey(
        @Query("keyword") request:String,
        @Query("page") page:Int
    ): Response<Search>


    /**
     * @return list of Film on Success [Releases]
     * @param[year] contains release year for search, for example movie name "2007"
     * @param[month] contains release month for search, for example movie name " JANUARY"
     * @param[page] page number
     */
    @GET("/api/v2.1/films/releases")
    suspend fun getReleases(
        @Query("year") year:Int,
        @Query("month") month:String,
        @Query("page") page:Int
    ): Response<Releases>
}