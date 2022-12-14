package happigin.inc.retrofit

import happigin.inc.domain.models.trump.TrumpResult
import happigin.inc.domain.models.dogs.DogsResult
import happigin.inc.domain.models.joke.Joke
import happigin.inc.domain.models.kinopoisk.KinopoiskResult
import happigin.inc.domain.models.kinopoisk.searhByKey.Search
import happigin.inc.domain.models.kinopoisk.trailer.Trailer
import happigin.inc.domain.models.news.News
import happigin.inc.domain.models.numberFacts.RandomNumber
import happigin.inc.domain.models.randomactivity.RandomActivityResult
import happigin.inc.domain.models.randomUser.RandomUser

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //random dogs
    @GET("/api/breeds/image/random")
    suspend fun getDogs() : Response<DogsResult>

    // get random joke
    @GET("/random_joke")
    suspend fun getJoke() : Response<Joke>

    //trump sheet
    @GET("/random/quote")
    suspend fun getTrumpRandom(
        @Query("X-RapidAPI-Key") key:String = "26a2be46bamsh1057b695a5d4f16p1421c5jsnaa3b0e5b2c51",
        @Query("X-RapidAPI-Host") host:String = "matchilling-tronald-dump-v1.p.rapidapi.com"
    ) : Response<TrumpResult>

    //get random activity
    @GET("/api/activity")
    suspend fun getRandomActivity() : Response<RandomActivityResult>

    //get random user
    @GET("/api")
    suspend fun getRandomUser() : Response<RandomUser>

    //get random number
    @GET("/1492/year?json=true&fragment=true")
    suspend fun getRandomNumber(
        @Query("X-RapidAPI-Key") key:String = "26a2be46bamsh1057b695a5d4f16p1421c5jsnaa3b0e5b2c51"
    ) : Response<RandomNumber>


    //get news
    @GET("v2/everything?apiKey=7ece7e8125cd4f3ea6b82f4f3e364592")
    suspend fun getNews(
        @Query("q") request:String
    ) : Response<News>

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
}