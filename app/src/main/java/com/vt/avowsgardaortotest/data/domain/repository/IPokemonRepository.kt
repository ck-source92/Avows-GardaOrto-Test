package com.vt.avowsgardaortotest.data.domain.repository

import com.vt.avowsgardaortotest.data.domain.model.Pokemon
import com.vt.avowsgardaortotest.data.dto.DetailsPokemonResponse
import com.vt.avowsgardaortotest.data.remote.network.Resource
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun getAllPokemon(): Flow<Resource<List<Pokemon>>>
    fun getOfflinePokemons(): Flow<List<Pokemon>>
    fun searchPokemon(query: String): Flow<List<Pokemon>>
    fun detailsPokemon(name: String): Flow<Resource<DetailsPokemonResponse>>
}