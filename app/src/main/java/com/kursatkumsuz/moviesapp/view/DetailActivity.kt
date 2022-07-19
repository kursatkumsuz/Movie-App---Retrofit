package com.kursatkumsuz.moviesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kursatkumsuz.moviesapp.R
import com.kursatkumsuz.moviesapp.Singleton
import com.kursatkumsuz.moviesapp.adapter.ImageRecyclerViewAdapter
import com.kursatkumsuz.moviesapp.databinding.ActivityDetailBinding
import com.kursatkumsuz.moviesapp.model.MovieModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter : ImageRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedMovie = Singleton.chosenMovie
        selectedMovie?.let {
            binding.titleText.text = it.title
            binding.overviewText.text = it.plot
            binding.imageView.load(it.image[2]) {
                crossfade(true)
            }

            binding.imageViewTwo.load(it.image[2]) {
                crossfade(true)
            }
            adapter = ImageRecyclerViewAdapter(it.image)
        }

        // Recyclerview

        binding.imageRecyclerView.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
        binding.imageRecyclerView.adapter = adapter

    }
}