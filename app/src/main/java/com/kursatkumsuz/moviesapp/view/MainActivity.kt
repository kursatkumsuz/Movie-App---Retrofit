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
    private val BASE_URL = "https://gist.githubusercontent.com/"
    private lateinit var adapter : RecyclerViewAdapter
    private lateinit var movieList : ArrayList<MovieModel>
    private lateinit var displayedList : ArrayList<MovieModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        displayedList = ArrayList()
        // Functions
        loadMovie()
        searchCoin()
        //RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun loadMovie() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MovieAPI::class.java)
        val call = service.getMovie()

        call.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        movieList = ArrayList(it)
                        movieList.let { list ->
                            displayedList.addAll(list)
                            adapter = RecyclerViewAdapter(displayedList)
                            binding.recyclerView.adapter = adapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    private fun searchCoin()
    {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty())
                {
                    displayedList.clear()
                    val search = newText.lowercase(Locale.getDefault())
                    movieList.forEach {
                        if(it.title.lowercase(Locale.getDefault()).contains(search)) {
                            displayedList.add(it)
                        }
                    }
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }  else
                {
                    displayedList.clear()
                    loadMovie()
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })
    }
}
