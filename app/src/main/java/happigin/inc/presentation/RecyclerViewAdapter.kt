package happigin.inc.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import happigin.inc.databinding.NewsCardBinding
import happigin.inc.domain.models.news.Articles

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    class ViewHolder(private val binding: NewsCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(item:Articles, id: Int){
            binding.apply {
               textView.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.setData(currentItem, position)
    }



    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private val differCallback = object : DiffUtil.ItemCallback<Articles>() {

        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }
    }
    val differ= AsyncListDiffer(this,differCallback )


}
