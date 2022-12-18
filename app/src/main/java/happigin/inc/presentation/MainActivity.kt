package happigin.inc.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import happigin.inc.app.appComponent
import happigin.inc.databinding.ActivityMainBinding
import happigin.inc.domain.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: RecyclerViewAdapter = RecyclerViewAdapter()
    @Inject
    lateinit var retrofit: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        binding.button.setOnClickListener(){
            // launching a new coroutine
            GlobalScope.launch(Dispatchers.Main) {
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
            }
        }
    }

}