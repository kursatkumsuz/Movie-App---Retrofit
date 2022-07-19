package com.kursatkumsuz.moviesapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kursatkumsuz.moviesapp.Singleton
import com.kursatkumsuz.moviesapp.databinding.RecyclerRowBinding
import com.kursatkumsuz.moviesapp.model.MovieModel
import com.kursatkumsuz.moviesapp.view.DetailActivity

class RecyclerViewAdapter(val movieList : ArrayList<MovieModel>) : RecyclerView.Adapter<RecyclerViewAdapter.MovieHolder>() {
    class MovieHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
       val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        val animation  = AnimationUtils.loadAnimation(holder.itemView.context , android.R.anim.slide_in_left)
        holder.itemView.startAnimation(animation)

        holder.binding.rowTextView.text = movieList[position].title
        holder.binding.rowRankText.text = movieList[position].rate
        holder.binding.rowTypeText.text = movieList[position].type
        holder.binding.rowImageView.load(movieList[position].image[0]) {
            crossfade(true)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context , DetailActivity::class.java)
            Singleton.chosenMovie = movieList[position]
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
       return movieList.size
    }


}