package happigin.inc.presentation.screens.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import happigin.inc.appComponent
import happigin.inc.databinding.ActivityMainBinding
import happigin.inc.presentation.adapters.NewsLoaderStateAdapter
import happigin.inc.presentation.adapters.RecyclerViewAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: RecyclerViewAdapter = RecyclerViewAdapter()

    @Inject
    lateinit var viewModelProvider: Provider<MainViewModel.Factory>

    private val viewModel: MainViewModel by viewModels { viewModelProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter.withLoadStateHeaderAndFooter(
            header = NewsLoaderStateAdapter(),
            footer = NewsLoaderStateAdapter()
        )
        adapter.addLoadStateListener { states: CombinedLoadStates ->
            binding.recycler.isVisible = states.refresh != LoadState.Loading
            binding.progressBar.isVisible = states.refresh == LoadState.Loading
        }
        binding.button.setOnClickListener() {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.setQuery(binding.searchText.text.toString())
                        viewModel.movie.collectLatest {
                            adapter.submitData(it)
                            binding.recycler.layoutManager?.scrollToPosition(0)
                        }
                }
            }
                //TODO("нет корректной обработки ошибок")
        }
    }
}

