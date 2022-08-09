package com.kursatkumsuz.moviesapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kursatkumsuz.moviesapp.model.MovieModel

@Dao
interface MovieDao {

    @Insert
    suspend fun insertAll(vararg movies : MovieModel) : List<Long>

    @Query("SELECT * FROM MovieModel")
    suspend fun getAllMovies() : List<MovieModel>

    @Query("SELECT * FROM MovieModel WHERE primaryKey = :movieId")
    suspend fun getMovie(movieId : Int) : MovieModel

    @Query("DELETE FROM MovieModel")
    suspend fun deleteAllMovies()

}