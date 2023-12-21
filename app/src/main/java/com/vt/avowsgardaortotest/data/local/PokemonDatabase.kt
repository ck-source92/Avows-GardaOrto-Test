package com.vt.avowsgardaortotest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vt.avowsgardaortotest.data.local.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 2, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}