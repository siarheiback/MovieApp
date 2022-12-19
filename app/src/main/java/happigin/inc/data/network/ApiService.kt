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
    @GET(GET_FILMS_BY_ID)
    suspend fun getMovieById(
       @Path(PATH_ID) id:Long
    ) : Response<KinopoiskResult>

    /**
     * @return [Trailer] on Success
     * @param[id] movie id
     */
    @GET(GET_TRAILER_BY_ID)
    suspend fun getTrailerById(
        @Path(PATH_ID) id:Long
    ): Response<Trailer>


    /**
     * @return list of Film on Success [Search]
     * @param[request] contains key words for search, for example movie name "Matrix"
     * @param[page] page number
     */
    @GET(GET_FILMS_BY_KEYWORD)
    suspend fun getMovieByKey(
        @Query(QUERY_KEYWORD) request:String,
        @Query(QUERY_PAGE) page:Int
    ): Response<Search>


    /**
     * @return list of Film on Success [Releases]
     * @param[year] contains release year for search, for example movie name "2007"
     * @param[month] contains release month for search, for example movie name " JANUARY"
     * @param[page] page number
     */
    @GET(GET_RELEASES_FILMS)
    suspend fun getReleases(
        @Query(QUERY_YEAR) year:Int,
        @Query(QUERY_MONTH) month:String,
        @Query(QUERY_PAGE) page:Int
    ): Response<Releases>

    companion object {
        private const val QUERY_YEAR = "year"
        private const val QUERY_MONTH = "month"
        private const val QUERY_PAGE = "page"
        private const val QUERY_KEYWORD = "keyword"

        private const val PATH_ID = "id"

        private const val GET_FILMS_BY_ID = "api/v2.2/films/{id}"
        private const val GET_TRAILER_BY_ID = "api/v2.2/films/{id}/videos"
        private const val GET_FILMS_BY_KEYWORD = "api/v2.1/films/search-by-keyword"
        private const val GET_RELEASES_FILMS = "/api/v2.1/films/releases"
    }
}