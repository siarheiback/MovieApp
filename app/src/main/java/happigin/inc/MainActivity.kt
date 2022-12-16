package happigin.inc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import happigin.inc.databinding.ActivityMainBinding
import happigin.inc.presentation.RecyclerViewAdapter
import happigin.inc.retrofit.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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
                try {
                    val result = quotesApi.getMovieByKey(binding.searchText.text.toString(), 1)
                    if (result.isSuccessful) {
                        adapter.differ.submitList(result.body()?.films)
                        Toast.makeText(this@MainActivity,"ok",Toast.LENGTH_SHORT).show()
                    } else {
                        throw IOException(result.code().toString())
                    }
                    Log.d("TAG ", result.toString())
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