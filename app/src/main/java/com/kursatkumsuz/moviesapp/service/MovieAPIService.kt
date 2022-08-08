package com.kursatkumsuz.moviesapp.service

import com.kursatkumsuz.moviesapp.model.MovieModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {

    private val BASE_URL = "https://gist.githubusercontent.com/"
    private var api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getData() : Single<List<MovieModel>> {
        return api.getMovie()
    }

}