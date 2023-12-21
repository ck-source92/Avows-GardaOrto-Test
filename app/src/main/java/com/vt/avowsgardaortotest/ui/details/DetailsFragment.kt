package com.vt.avowsgardaortotest.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.vt.avowsgardaortotest.data.remote.network.Resource
import com.vt.avowsgardaortotest.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val detailsViewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val receiveBundle = arguments?.getString("pokemon_name")
        // TODO: Don't Forget Attach Pokemon Name
        detailsViewModel.fetchPokemonDetails("bulbasaur")
        observerView()
    }

    private fun observerView() {
        lifecycleScope.launch {
            detailsViewModel.pokemonDetails.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val details = resource.data
                        println("awkoawkwa $details")
                        println("awkoawkwaa ablty ${details?.abilities}")
                    }

                    is Resource.Error -> {
                        val errorMessage = resource.message
                        // Handle error case
                        // You might want to show an error message or take appropriate action
                        Toast.makeText(
                            requireActivity(),
                            "Error: $errorMessage",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Resource.Loading -> {
                        // Handle loading state if needed
                        println("awkoawkwa loading")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}