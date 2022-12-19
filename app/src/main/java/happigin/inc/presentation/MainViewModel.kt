package happigin.inc.presentation

import androidx.lifecycle.*
import androidx.paging.*
import happigin.inc.domain.models.kinopoisk.searhByKey.Film
import happigin.inc.domain.paging.MoviePageSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider

class MainViewModel @Inject constructor(
    private val pagingSourceFactory: MoviePageSource.Factory
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    /* val movie: Flow<PagingData<Film>> = Pager(
         PagingConfig(
             pageSize = 20,
             initialLoadSize = 20,
         ),
     ) {
         pagingSourceFactory.create(query.value)
     }.flow.cachedIn(viewModelScope)
 */

    @OptIn(ExperimentalCoroutinesApi::class)
    val movie: Flow<PagingData<Film>> = query
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)


    private fun newPager(query: String): Pager<Int, Film> {
        return Pager(PagingConfig(20, initialLoadSize = 20, maxSize = 200)) {
            pagingSourceFactory.create(query)
        }
    }

    fun setQuery(query: String) {
        _query.tryEmit(query)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val viewModerProvider: Provider<MainViewModel>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainViewModel::class.java)
            return viewModerProvider.get() as T
        }
    }
}
