package com.vt.avowsgardaortotest.data.domain.usecases

import com.vt.avowsgardaortotest.data.domain.model.Pokemon
import com.vt.avowsgardaortotest.data.remote.network.Resource
import kotlinx.coroutines.flow.Flow

interface IPokemonUsecases {
    fun getAllPokemon(): Flow<Resource<List<Pokemon>>>
    fun getOfflinePokemons(): Flow<List<Pokemon>>
    fun searchPokemon(query: String): Flow<List<Pokemon>>
}