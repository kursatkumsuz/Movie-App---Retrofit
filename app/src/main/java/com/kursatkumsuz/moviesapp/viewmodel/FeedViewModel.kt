package com.kursatkumsuz.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursatkumsuz.moviesapp.model.MovieModel
import com.kursatkumsuz.moviesapp.service.MovieAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {
    var movieList = MutableLiveData<List<MovieModel>>()
    private val apiService = MovieAPIService()
    private val disposable = CompositeDisposable()

    fun loadData() {
        getDataFromAPI()
    }
    fun getDataFromAPI() {
        disposable.add(
            apiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<MovieModel>>() {
                    override fun onSuccess(t: List<MovieModel>) {
                        movieList.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }
}