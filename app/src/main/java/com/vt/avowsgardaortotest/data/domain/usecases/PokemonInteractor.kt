package com.vt.avowsgardaortotest.data.domain.usecases

import com.vt.avowsgardaortotest.data.domain.model.Pokemon
import com.vt.avowsgardaortotest.data.domain.repository.IPokemonRepository
import com.vt.avowsgardaortotest.data.remote.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonInteractor @Inject constructor(private val iPokemonRepository: IPokemonRepository) :
    IPokemonUsecases {
    override fun getAllPokemon(): Flow<Resource<List<Pokemon>>> {
        return iPokemonRepository.getAllPokemon()
    }

    override fun getOfflinePokemons(): Flow<List<Pokemon>> {
        return iPokemonRepository.getOfflinePokemons()
    }
}