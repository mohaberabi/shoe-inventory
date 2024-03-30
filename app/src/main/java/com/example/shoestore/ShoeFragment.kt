package com.example.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.databinding.FragmentInstructionsBinding
import com.example.shoestore.databinding.FragmentShoeBinding


class ShoeFragment : Fragment() {


    private lateinit var viewModel: ShoeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shoe,
                container,
                false
            )


        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]

        viewModel.shoes.observe(
            viewLifecycleOwner
        ) { shoes ->

            for (item in shoes) {

                binding.shoeLinearLayout.addView(
                    addView(
                        item.name,
                        onClick = {

                            viewModel.onShoePressed(item)

                        },
                    )
                )
            }

        }

        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {

            findNavController().navigate(ShoeFragmentDirections.actionShoeFragmentToShoeDetailFragment())
        }



        viewModel.goToDetail.observe(viewLifecycleOwner) {

            if (it) {
                findNavController().navigate(ShoeFragmentDirections.actionShoeFragmentToShoeDetailFragment())
                viewModel.resetGoToDetail()
            }
        }
        return binding.root
    }


    private fun addView(title: String, onClick: () -> Unit = {}): View {
        val linearLayout = LinearLayout(context)
        val textView = TextView(context)
        textView.text = title
        linearLayout.addView(textView)
        linearLayout.setOnClickListener {
            onClick.invoke()
        }
        return linearLayout
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

}