package com.kursatkumsuz.moviesapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kursatkumsuz.moviesapp.model.MovieModel
import com.kursatkumsuz.moviesapp.service.MovieDataBase
import kotlinx.coroutines.launch

class PopUpViewModel (application: Application) : BaseViewModel(application) {
     var imageList = MutableLiveData<ArrayList<String>>()

    fun getImageFromRoom(primaryKey: Int) {
       launch {
           val dao = MovieDataBase(getApplication()).movieDao()
           val image = dao.getMovie(primaryKey)
           imageList.value = image.image
       }
    }
}