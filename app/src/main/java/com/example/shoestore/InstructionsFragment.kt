package com.example.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentInstructionsBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_instructions,
                container,
                false
            )


        binding.inventoryButton.setOnClickListener {


            findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeFragment())
        }
        return binding.root
    }


}