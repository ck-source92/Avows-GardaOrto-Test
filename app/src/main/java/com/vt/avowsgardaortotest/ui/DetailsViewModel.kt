package com.vt.avowsgardaortotest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vt.avowsgardaortotest.data.domain.usecases.IPokemonUsecases
import com.vt.avowsgardaortotest.data.dto.DetailsPokemonResponse
import com.vt.avowsgardaortotest.data.remote.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val usecases: IPokemonUsecases) :
    ViewModel() {

    private val _pokemonDetails =
        MutableStateFlow<Resource<DetailsPokemonResponse>>(Resource.Loading())
    val pokemonDetails: StateFlow<Resource<DetailsPokemonResponse>> get() = _pokemonDetails

    fun fetchPokemonDetails(pokemonName: String) {
        viewModelScope.launch {
            usecases.detailsPokemon(pokemonName)
                .collect { resource ->
                    _pokemonDetails.value = resource
                }
        }
    }
}