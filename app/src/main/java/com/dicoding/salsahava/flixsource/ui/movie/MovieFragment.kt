package com.dicoding.salsahava.flixsource.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.salsahava.flixsource.core.data.Resource
import com.dicoding.salsahava.flixsource.core.ui.MovieAdapter
import com.dicoding.salsahava.flixsource.databinding.FragmentMovieBinding
import com.dicoding.salsahava.flixsource.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_FLIX, selectedData)
                startActivity(intent)
            }

            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE

                        is Resource.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            movieAdapter.setMovies(movies.data)
                        }

                        is Resource.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })

            binding?.rvMovie?.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
    }
}