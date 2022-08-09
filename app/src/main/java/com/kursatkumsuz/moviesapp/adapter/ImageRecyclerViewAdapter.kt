package com.kursatkumsuz.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kursatkumsuz.moviesapp.databinding.RowImageBinding
import com.kursatkumsuz.moviesapp.fragment.DetailFragmentDirections

class ImageRecyclerViewAdapter(val imageList: List<String>, val key: Int) :
    RecyclerView.Adapter<ImageRecyclerViewAdapter.ImageHolder>() {
    class ImageHolder(val binding: RowImageBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding = RowImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {

        holder.binding.rowImage.load(imageList[position]) {
            crossfade(true)
        }

        holder.itemView.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToPopUpFragment(key, position)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}