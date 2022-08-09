package com.kursatkumsuz.moviesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.kursatkumsuz.moviesapp.R
import com.kursatkumsuz.moviesapp.adapter.ImageRecyclerViewAdapter
import com.kursatkumsuz.moviesapp.databinding.FragmentDetailBinding
import com.kursatkumsuz.moviesapp.viewmodel.DetailViewModel

class DetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private lateinit var viewModel : DetailViewModel
    private lateinit var adapter : ImageRecyclerViewAdapter
    private var primaryKey : Int = 1
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            primaryKey = DetailFragmentArgs.fromBundle(it).key
        }
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        viewModel.getFromRoom(primaryKey)
        println("Data: $primaryKey")
        binding.imageRecyclerView.layoutManager = LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , false)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.selectedMovie.observe(viewLifecycleOwner , Observer { movie ->
            movie?.let {
                binding.imageView.load(movie.image[0])
                binding.imageViewTwo.load(movie.image[0])
                binding.titleText.text = movie.title
                binding.overviewText.text = movie.plot
                adapter = ImageRecyclerViewAdapter(movie.image)
                binding.imageRecyclerView.adapter = adapter
            }
        })
    }
}