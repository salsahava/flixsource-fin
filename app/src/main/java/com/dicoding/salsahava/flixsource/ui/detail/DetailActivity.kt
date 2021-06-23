package com.dicoding.salsahava.flixsource.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.salsahava.flixsource.R
import com.dicoding.salsahava.flixsource.core.data.Resource
import com.dicoding.salsahava.flixsource.core.domain.model.Movie
import com.dicoding.salsahava.flixsource.core.utils.formatter.DetailFormatter
import com.dicoding.salsahava.flixsource.databinding.ActivityDetailBinding
import com.dicoding.salsahava.flixsource.databinding.ContentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailContentBinding: ContentDetailBinding

    private var menu: Menu? = null
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.contentDetail

        setContentView(activityDetailBinding.root)

        val movieExtra = intent.getParcelableExtra<Movie>(EXTRA_FLIX)
        if (movieExtra != null) {
            viewModel.setSelectedItem(movieExtra.movieId)
        }

        viewModel.movie.observe(this, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        showLoading(true)
                        showContent(false)
                    }

                    is Resource.Success -> if (movie.data != null) {
                        showLoading(false)
                        showContent(true)
                        populateDetailWithMovie(movie.data!!)
                    }

                    is Resource.Error -> {
                        showLoading(false)
                        showErrorToast()
                    }
                }
            }
        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun populateDetailWithMovie(movie: Movie) {

        detailContentBinding.apply {
            tvTitle.text = movie.title
            tvReleaseDate.text = DetailFormatter.releaseDateFormatter(movie.releaseDate)
            tvGenre.text = movie.genres
            tvDescription.text = movie.description

            cvScore.tvScore.text = getString(R.string.rating, movie.score)
            cvDuration.tvDuration.text = DetailFormatter.durationFormatter(movie.duration)

            Glide.with(this@DetailActivity)
                .load(DetailFormatter.posterFormatter(movie.poster))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgPoster)
        }

        supportActionBar?.title = movie.title
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        viewModel.movie.observe(this, { movie ->
            setFavoriteMovie(movie)
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            viewModel.setMovieFavorite()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteMovie(movie: Resource<Movie>) {
        when (movie) {
            is Resource.Loading -> showLoading(true)

            is Resource.Success -> if (movie.data != null) {
                showLoading(false)
                val state = movie.data!!.favorited
                setFavoriteState(state)
            }

            is Resource.Error -> {
                showLoading(false)
                showErrorToast()
            }
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return

        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) menuItem?.icon =
            ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_filled_white_24)
        else menuItem?.icon =
            ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_white_24)
    }

    private fun showLoading(state: Boolean) {
        if (state) activityDetailBinding.progressBar.visibility = View.VISIBLE
        else activityDetailBinding.progressBar.visibility = View.GONE
    }

    private fun showContent(state: Boolean) {
        if (state) activityDetailBinding.contentDetailContainer.visibility = View.VISIBLE
        else activityDetailBinding.contentDetailContainer.visibility = View.INVISIBLE
    }

    private fun showErrorToast() {
        Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_FLIX = "extra_flix"
    }
}
