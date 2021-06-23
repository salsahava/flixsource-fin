package com.dicoding.salsahava.flixsource.favorites

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.salsahava.flixsource.core.ui.MovieAdapter
import com.dicoding.salsahava.flixsource.favorites.databinding.ActivityFavoritesBinding
import com.dicoding.salsahava.flixsource.favorites.di.favoritesModule
import com.dicoding.salsahava.flixsource.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding

    private val viewModel: FavoriteViewModel by viewModel()
    private val favMovieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoritesModule)

        favMovieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@FavoritesActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_FLIX, selectedData)
            startActivity(intent)
        }

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getFavoriteMovies().observe(this, { movies ->
            binding.progressBar.visibility = View.GONE
            favMovieAdapter.setMovies(movies)

            if (movies.isEmpty()) Toast.makeText(
                this,
                "You don't have any favorite movies",
                Toast.LENGTH_SHORT
            ).show()
        })

        binding.rvFavMovies.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
            adapter = favMovieAdapter
        }

        supportActionBar?.title = "Favorite Movies"
    }
}