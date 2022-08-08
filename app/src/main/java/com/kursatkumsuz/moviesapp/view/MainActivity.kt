package com.kursatkumsuz.moviesapp.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kursatkumsuz.moviesapp.adapter.RecyclerViewAdapter
import com.kursatkumsuz.moviesapp.databinding.ActivityMainBinding
import com.kursatkumsuz.moviesapp.model.MovieModel
import com.kursatkumsuz.moviesapp.service.MovieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

}
