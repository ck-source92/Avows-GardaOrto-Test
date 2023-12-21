package com.vt.avowsgardaortotest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vt.avowsgardaortotest.data.domain.model.Pokemon
import com.vt.avowsgardaortotest.data.domain.usecases.IPokemonUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val iPokemonUsecases: IPokemonUsecases) :
    ViewModel() {
    val pokemon = iPokemonUsecases.getAllPokemon().asLiveData()
    val pokemonOffline = iPokemonUsecases.getOfflinePokemons().asLiveData()

    fun searchPokemon(searchQuery: String): LiveData<List<Pokemon>> {
        return iPokemonUsecases.searchPokemon(searchQuery).asLiveData()
    }


}