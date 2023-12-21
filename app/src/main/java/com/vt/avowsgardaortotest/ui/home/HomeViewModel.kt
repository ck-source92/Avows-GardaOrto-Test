package com.vt.avowsgardaortotest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vt.avowsgardaortotest.data.domain.usecases.IPokemonUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val iPokemonUsecases: IPokemonUsecases) :
    ViewModel() {
    val pokemon = iPokemonUsecases.getAllPokemon().asLiveData()
    val pokemonOffline = iPokemonUsecases.getOfflinePokemons().asLiveData()
}