package com.kursatkumsuz.moviesapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursatkumsuz.moviesapp.model.MovieModel
import com.kursatkumsuz.moviesapp.service.MovieAPIService
import com.kursatkumsuz.moviesapp.service.MovieDataBase
import com.kursatkumsuz.moviesapp.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {
    var movieList = MutableLiveData<List<MovieModel>>()
    private val apiService = MovieAPIService()
    private val disposable = CompositeDisposable()
    private var isShowing = true
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L
    fun loadData() {
        val updateTime = customSharedPreferences.getTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getFromSQL()
        } else {
            getDataFromAPI()
        }

    }

    fun getFromSQL() {
        launch {
            val movies = MovieDataBase(getApplication()).movieDao().getAllMovies()
            showMovies(movies)
            Toast.makeText(getApplication() , "FROM SQL" , Toast.LENGTH_LONG).show()
        }
    }

    fun getDataFromAPI() {
        disposable.add(
            apiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<MovieModel>>() {
                    override fun onSuccess(t: List<MovieModel>) {
                        storeInSqLite(t)
                        Toast.makeText(getApplication() , "FROM INTERNET" , Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showMovies(list: List<MovieModel>) {
        movieList.value = list
    }

    private fun storeInSqLite(list: List<MovieModel>) {

        launch {
            val dao = MovieDataBase(getApplication()).movieDao()
            dao.deleteAllMovies()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].primaryKey = listLong[i].toInt()
                i += 1
            }
            showMovies(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }

}