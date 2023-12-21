package com.vt.avowsgardaortotest.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vt.avowsgardaortotest.R
import com.vt.avowsgardaortotest.data.domain.model.Pokemon
import com.vt.avowsgardaortotest.data.remote.network.Resource
import com.vt.avowsgardaortotest.databinding.FragmentHomeBinding
import com.vt.avowsgardaortotest.utils.NetworkError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val pokemonAdapter by lazy { HomeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchPokemon.setOnQueryTextListener(this@HomeFragment)
        observerView()
    }

    private fun observerView() {
        viewModel.pokemon.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is Resource.Success -> {
                        binding.progressBar.isVisible = false
                        setupRecyclerView(it.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            requireActivity(),
                            "No Internet Connection\n" + it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        NetworkError(requireActivity()).showNoConnectionDialog()
                    }

                    else -> {}
                }
            }
        }
        viewModel.pokemonOffline.observe(viewLifecycleOwner) {
            Log.d("TEST", it.toString())
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(pokemon: List<Pokemon>?) {
        with(binding) {
            listPokemon.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            listPokemon.setHasFixedSize(true)
            listPokemon.adapter = pokemonAdapter
        }
        pokemonAdapter.submitList(pokemon)
        pokemonAdapter.onClickListener = {
            val mBundle = Bundle()
            mBundle.putString("pokemon_name", it.name)
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, mBundle)
        }
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.searchPokemon(searchQuery).observe(viewLifecycleOwner) { list ->
            list.let {
                pokemonAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchDatabase(newText)
        }
        return true
    }

}