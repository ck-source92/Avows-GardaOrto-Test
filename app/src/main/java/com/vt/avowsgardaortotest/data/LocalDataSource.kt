package com.vt.avowsgardaortotest.data

import com.vt.avowsgardaortotest.data.local.PokemonDao
import com.vt.avowsgardaortotest.data.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val pokemonDao: PokemonDao) {
    fun getAllPokemon(): Flow<List<PokemonEntity>> = pokemonDao.getAllPokemon()
    suspend fun insertPokemonToLocal(pokemon: List<PokemonEntity>) =
        pokemonDao.insertPokemon(pokemon)

    fun getOfflinePokemon(): Flow<List<PokemonEntity>> = pokemonDao.getOfflinePokemon()
}