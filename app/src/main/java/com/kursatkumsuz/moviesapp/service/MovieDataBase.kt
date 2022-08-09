package com.kursatkumsuz.moviesapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kursatkumsuz.moviesapp.model.MovieModel
import com.kursatkumsuz.moviesapp.util.ArrayListConverter

@TypeConverters(ArrayListConverter::class)
@Database(entities = arrayOf(MovieModel::class) , version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao() : MovieDao

    companion object {
        @Volatile private var instance : MovieDataBase? = null

        private var lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDB(context).also {database ->
                instance = database
            }
        }

        private fun makeDB(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieDataBase::class.java,
            "movieDataBase"
        ).build()
    }
}