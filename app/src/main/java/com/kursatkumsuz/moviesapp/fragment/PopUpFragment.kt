package com.kursatkumsuz.moviesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.kursatkumsuz.moviesapp.R
import com.kursatkumsuz.moviesapp.databinding.FragmentPopUpBinding
import com.kursatkumsuz.moviesapp.viewmodel.PopUpViewModel


class PopUpFragment : DialogFragment() {

    private lateinit var viewModel: PopUpViewModel
    private var position = 0
    private var key = 0
    private var _binding: FragmentPopUpBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            position = PopUpFragmentArgs.fromBundle(it).position
            key = PopUpFragmentArgs.fromBundle(it).key
        }
        viewModel = ViewModelProvider(this)[PopUpViewModel::class.java]
        viewModel.getImageFromRoom(key)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.imageList.observe(viewLifecycleOwner, Observer { image ->
            image?.let {
                binding.imageView.load(image[position])
            }
        })
    }
}