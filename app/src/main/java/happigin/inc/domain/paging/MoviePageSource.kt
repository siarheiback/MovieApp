package happigin.inc.domain.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import happigin.inc.data.network.models.kinopoisk.searhByKey.Film
import happigin.inc.data.network.ApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException


class MoviePageSource @AssistedInject constructor(
    private val api: ApiService,
    @Assisted("keyword") private val query: String
) : PagingSource<Int, Film>() {


    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        val lastPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(lastPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        val response = api.getMovieByKey(query, page)
        return try {
            if (response.isSuccessful) {
                val result = checkNotNull(response.body()).films
                val nextPage = if (result.size < pageSize) null else page + 1
                val prevPage = if (page == 1) null else page - 1
                LoadResult.Page(result, prevPage, nextPage)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("keyword") query: String): MoviePageSource
    }
}
