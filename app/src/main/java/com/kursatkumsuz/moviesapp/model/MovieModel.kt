package com.kursatkumsuz.moviesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class MovieModel(
    @ColumnInfo(name = "name")
    @SerializedName("Title")
    val title: String,

    @ColumnInfo(name = "image")
    @SerializedName("Images")
    val image: ArrayList<String>,

    @ColumnInfo(name = "rate")
    @SerializedName("imdbRating")
    val rate: String,

    @ColumnInfo(name = "plot")
    @SerializedName("Plot")
    val plot: String,

    @ColumnInfo(name = "type")
    @SerializedName("Type")
    val type: String
) {
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int = 0
}


