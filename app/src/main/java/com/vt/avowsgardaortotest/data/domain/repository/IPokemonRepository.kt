package com.vt.avowsgardaortotest.data.domain.repository

import com.vt.avowsgardaortotest.data.domain.model.Pokemon
import com.vt.avowsgardaortotest.data.remote.network.Resource
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun getAllPokemon(): Flow<Resource<List<Pokemon>>>
    fun getOfflinePokemons(): Flow<List<Pokemon>>
}