package com.kursatkumsuz.moviesapp.service

import com.kursatkumsuz.moviesapp.model.MovieModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET


interface MovieAPI {

    //https://gist.githubusercontent.com/
    // saniyusuf/406b843afdfb9c6a86e25753fe2761f4/raw/523c324c7fcc36efab8224f9ebb7556c09b69a14/Film.JSON

    @GET("saniyusuf/406b843afdfb9c6a86e25753fe2761f4/raw/523c324c7fcc36efab8224f9ebb7556c09b69a14/Film.JSON")
    fun getMovie() : Single<List<MovieModel>>
}