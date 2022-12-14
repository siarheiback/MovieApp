package happigin.inc.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelper {
    val gson = GsonBuilder().setLenient().create()

    private const val jokeUrl = "https://official-joke-api.appspot.com/"
    private const val dogsUrl = "https://dog.ceo/"
    private const val trumpUrl = "https://matchilling-tronald-dump-v1.p.rapidapi.com/"
    private const val activityUrl = "https://www.boredapi.com/"
    private const val randomUserUrl = "https://randomuser.me/"
    private const val randomNumberUrl = "https://numbersapi.p.rapidapi.com/"
    private const val newsUrl = "https://newsapi.org/"
    private const val movieUrl = "https://kinopoiskapiunofficial.tech/"

    fun getInstance(): Retrofit {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()


        return Retrofit.Builder()
            .baseUrl(movieUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(mOkHttpClient)
            .build()
    }
    /*val gson = GsonBuilder().setLenient().create()
        // private const val baseUrl = "https://quotable.io/"
        //private const val baseUrl = "https://api.tronalddump.io/random/"
        private const val baseUrl = "https://boredapi.com/api/"
        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(jokeUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }*/
    }
