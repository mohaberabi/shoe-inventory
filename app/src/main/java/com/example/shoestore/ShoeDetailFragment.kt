package com.example.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoestore.databinding.FragmentShoeDetailBinding


class ShoeDetailFragment : Fragment() {
    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding: FragmentShoeDetailBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shoe_detail,
                container,
                false
            )



        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]


        binding.viewmodel = viewModel
        binding.lifecycleOwner = this


        viewModel.doneAdding.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
                viewModel.resetDoneAdding()
            }
        }
        return binding.root

    }

}