package com.dicoding.salsahava.flixsource.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.salsahava.flixsource.core.R
import com.dicoding.salsahava.flixsource.core.databinding.ItemFlixBinding
import com.dicoding.salsahava.flixsource.core.domain.model.Movie
import com.dicoding.salsahava.flixsource.core.utils.formatter.DetailFormatter

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ItemViewHolder>() {

    private var movieList = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.movieList.clear()
        this.movieList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemMovieBinding =
            ItemFlixBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movieList.size

    inner class ItemViewHolder(private val binding: ItemFlixBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemReleaseDate.text = DetailFormatter.releaseDateFormatter(movie.releaseDate)
                tvItemDescription.text = movie.description

                Glide.with(itemView.context)
                    .load(DetailFormatter.posterFormatter(movie.poster))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                try {
                    onItemClick?.invoke(movieList[absoluteAdapterPosition])
                } catch (e: Exception) {
                    Toast.makeText(itemView.context, "Something went wrong. Please go back to Movies and try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}