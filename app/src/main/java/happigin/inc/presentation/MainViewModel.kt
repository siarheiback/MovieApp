package happigin.inc.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import happigin.inc.domain.models.kinopoisk.searhByKey.Film
import happigin.inc.domain.paging.MoviePageSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

class MainViewModel @Inject constructor(
    private val pagingSourceFactory: MoviePageSource.Factory
) : ViewModel() {


    val movie: StateFlow<PagingData<Film>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
        ),
    ) {
        pagingSourceFactory.create(query = "test")
    }.flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


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
