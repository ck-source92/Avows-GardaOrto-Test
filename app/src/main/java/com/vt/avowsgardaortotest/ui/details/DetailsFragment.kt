package com.vt.avowsgardaortotest.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vt.avowsgardaortotest.R
import com.vt.avowsgardaortotest.data.dto.AbilitiesItem
import com.vt.avowsgardaortotest.data.dto.StatsItem
import com.vt.avowsgardaortotest.data.remote.network.Resource
import com.vt.avowsgardaortotest.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val detailsViewModel by viewModels<DetailsViewModel>()
    private val abilityAdapter by lazy { DetailAdapter() }
    private val baseStatsAdapter by lazy { BaseStatsAdapter() }
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
        if (receiveBundle != null) {
            binding.namePokemon.text = receiveBundle.toString()
            detailsViewModel.fetchPokemonDetails(receiveBundle)
        }
        observerView()
    }

    @SuppressLint("SetTextI18n")
    private fun observerView() {
        lifecycleScope.launch {
            detailsViewModel.pokemonDetails.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        binding.loading.isVisible = false
                        val details = resource.data
                        Glide.with(requireActivity())
                            .load(details?.sprites?.frontShiny)
                            .error(R.mipmap.ic_launcher)
                            .into(binding.imageView)
                        setupAbilityRecyclerView(details?.abilities)
                        setupBaseStatsRecyclerView(details?.stats)
                    }

                    is Resource.Error -> {
                        binding.loading.isVisible = false
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
                        binding.loading.isVisible = true
                    }
                }
            }
        }
    }

    private fun setupAbilityRecyclerView(data: List<AbilitiesItem>?) {
        with(binding) {
            listAbility.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            listAbility.setHasFixedSize(true)
            listAbility.adapter = abilityAdapter
        }
        abilityAdapter.submitList(data)
    }

    private fun setupBaseStatsRecyclerView(data: List<StatsItem>?) {
        with(binding) {
            listBaseStats.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            listBaseStats.setHasFixedSize(true)
            listBaseStats.adapter = baseStatsAdapter
        }
        baseStatsAdapter.submitList(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun getBaseStatAtIndex(pokemonData: Map<String, Any>, index: Int): Int? {
        val stats = pokemonData["stats"] as? List<Map<String, Any>> ?: return null

        return if (index in 0 until stats.size) {
            stats[index]["base_stat"] as? Int
        } else {
            null
        }
    }
}