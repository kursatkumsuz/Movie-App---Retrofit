package com.kursatkumsuz.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kursatkumsuz.moviesapp.databinding.RecyclerRowBinding
import com.kursatkumsuz.moviesapp.fragment.FeedFragmentDirections
import com.kursatkumsuz.moviesapp.model.MovieModel

class RecyclerViewAdapter(val movieList: List<MovieModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MovieHolder>() {
    class MovieHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        val animation = AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)
        holder.itemView.startAnimation(animation)

        holder.binding.rowTextView.text = movieList[position].title
        holder.binding.rowRankText.text = movieList[position].rate
        holder.binding.rowTypeText.text = movieList[position].type
        holder.binding.rowImageView.load(movieList[position].image[0]) {
            crossfade(true)
        }
        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(key = movieList[position].primaryKey)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


}