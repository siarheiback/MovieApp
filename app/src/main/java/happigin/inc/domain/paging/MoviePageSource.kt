package happigin.inc.domain.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import happigin.inc.domain.models.kinopoisk.searhByKey.Film
import happigin.inc.domain.retrofit.ApiService
import retrofit2.HttpException
import javax.inject.Inject

class MoviePageSource@AssistedInject constructor(
    private val api:ApiService,
    @Assisted("keyword") private val query: String
): PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        val lastPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(lastPosition) ?:return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        Log.d("GGG", "page size: $pageSize")
        Log.d("GGG", "page num: $page")
        val response = api.getMovieByKey(query,page)
        return if (response.isSuccessful){
            val result = checkNotNull(response.body()).films
            Log.d("GGG", "films count: ${result.size}")
            val nextPage = if(result.size<pageSize) null else page+1
            val prevPage = if (page ==1) null else page-1
            LoadResult.Page (result, nextPage, prevPage)
        }else{
            LoadResult.Error(HttpException(response))
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("keyword") query: String): MoviePageSource
    }
}