package com.vt.avowsgardaortotest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vt.avowsgardaortotest.data.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemons")
    fun getAllPokemon(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemons")
    fun getOfflinePokemon(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons WHERE name LIKE :searchQuery")
    fun searchPokemon(searchQuery: String): Flow<List<PokemonEntity>>
}