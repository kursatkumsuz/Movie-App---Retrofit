package com.kursatkumsuz.moviesapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.kursatkumsuz.moviesapp.model.MovieModel
import com.kursatkumsuz.moviesapp.service.MovieDataBase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val selectedMovie = MutableLiveData<MovieModel>()

    fun getFromRoom(primaryKey : Int) {
        launch {
            val dao = MovieDataBase(getApplication()).movieDao()
            val movie = dao.getMovie(primaryKey)
            selectedMovie.value = movie
        }
    }
}