package happigin.inc.di.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import happigin.inc.domain.retrofit.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * This module provide retrofit builder with supporting components
 * @author siarheiback
 */

@Module
class RetrofitModule {

    companion object {
        private const val address = "https://kinopoiskapiunofficial.tech/"
        private const val key = "d31e14a0-297c-414e-9a32-be7d2e5075b3"
        private const val accept = "application/json"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideHeader(): Interceptor {
        return Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("Accept", accept)
            builder.header("x-api-key", key)
            return@Interceptor chain.proceed(builder.build())
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        mHttpLoggingInterceptor: HttpLoggingInterceptor,
        header: Interceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .addInterceptor(header)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(mOkHttpClient: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(address)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

}