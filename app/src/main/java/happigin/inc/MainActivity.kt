package happigin.inc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import happigin.inc.databinding.ActivityMainBinding
import happigin.inc.presentation.RecyclerViewAdapter
import happigin.inc.retrofit.ApiService
import happigin.inc.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: RecyclerViewAdapter = RecyclerViewAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val quotesApi = RetrofitHelper.getInstance().create(ApiService::class.java)
        val recyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter
        binding.button.setOnClickListener(){
            // launching a new coroutine
            GlobalScope.launch(Dispatchers.Main) {
                binding.progressBar.visibility= View.VISIBLE
                val result = quotesApi.getMovieByKey(binding.searchText.text.toString(), 1)
                adapter.differ.submitList(result.body()?.films)
                Log.d("TAG ", result.body().toString())
                Log.d("REL",quotesApi.getReleases(2022,"JANUARY",2).body().toString())
                binding.progressBar.visibility= View.GONE
            }


        }



    }

}