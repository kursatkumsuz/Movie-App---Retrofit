package com.kursatkumsuz.moviesapp.model

import com.google.gson.annotations.SerializedName

data class MovieModel(

    @SerializedName("Title")
    val title : String,
    @SerializedName("Images")
    val image : List<String>,
    @SerializedName("imdbRating")
    val rate : String,
    @SerializedName("Plot")
    val plot : String,
    @SerializedName("Type")
    val type : String

)
