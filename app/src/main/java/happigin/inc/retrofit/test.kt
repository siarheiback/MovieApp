package happigin.inc.retrofit

import android.util.Log
import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val quotesApi = RetrofitHelper.getInstance().create(ApiService::class.java)

        //val result = quotesApi.getMovieById()
        //println(result.body()?.toString())

}