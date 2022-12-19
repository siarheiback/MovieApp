package happigin.inc.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import happigin.inc.R
import happigin.inc.databinding.NewsCardBinding
import happigin.inc.domain.models.kinopoisk.searhByKey.Film

class RecyclerViewAdapter :
    PagingDataAdapter<Film, RecyclerViewAdapter.FilmViewHolder>(DifferCallback) {

    class FilmViewHolder(private val binding: NewsCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(item: Film?) {
            binding.apply {
                textView.text = item?.nameRu
                imageView.load(item?.posterUrlPreview) {
                    crossfade(true)
                    placeholder(R.drawable.ic_baseline_change_circle_24)
                }
                description.text = item?.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsCardBinding.inflate(inflater, parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
             holder.setData(getItem(position))
    }

    private object DifferCallback : DiffUtil.ItemCallback<Film>() {

        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.filmId == newItem.filmId  && oldItem.nameRu == newItem.nameRu
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }
    }

}
