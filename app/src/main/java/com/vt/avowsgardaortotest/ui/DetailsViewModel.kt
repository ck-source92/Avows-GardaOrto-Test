package com.vt.avowsgardaortotest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vt.avowsgardaortotest.data.repository.PokemonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val pokemonRepositoryImpl: PokemonRepositoryImpl) :
    ViewModel() {

    fun detailsPokemon() {
        viewModelScope.launch {
            pokemonRepositoryImpl.getDetailsPokemon()
        }
    }
}