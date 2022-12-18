package happigin.inc.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import happigin.inc.app.appComponent
import happigin.inc.databinding.ActivityMainBinding
import happigin.inc.domain.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
    lateinit var retrofit: ApiService

    @Inject
    lateinit var viewModelProvider: Provider<MainViewModel.Factory>

    private val viewModel:MainViewModel by viewModels{viewModelProvider.get()}

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        binding.button.setOnClickListener(){

            GlobalScope.launch(Dispatchers.Main) {
                val result = retrofit.getMovieByKey("test",1)
                Log.d("GGG", result.body()?.pagesCount.toString())
                viewModel.movie.collectLatest(adapter::submitData)

            }
         /*   GlobalScope.launch(Dispatchers.Main) {
                binding.progressBar.visibility= View.VISIBLE
                try {
                    val result = retrofit.getMovieByKey(binding.searchText.text.toString(), 1)
                    if (result.isSuccessful) {
                        adapter.differ.submitList(result.body()?.films)
                    } else {
                        throw IOException(result.code().toString())
                    }
                    binding.progressBar.visibility = View.GONE
                }
                catch (e: HttpException) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_SHORT).show()
                }catch (e: IOException){
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_SHORT).show()
                }
            }*/
        }
    }

}