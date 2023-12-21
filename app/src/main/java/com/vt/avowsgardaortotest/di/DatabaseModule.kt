package com.vt.avowsgardaortotest.di

import android.content.Context
import androidx.room.Room
import com.vt.avowsgardaortotest.data.local.PokemonDao
import com.vt.avowsgardaortotest.data.local.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PokemonDatabase = Room.databaseBuilder(
        context, PokemonDatabase::class.java, "AvowsPokemon.db"
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    fun providePokemonDao(pokemonDatabase: PokemonDatabase): PokemonDao {
        return pokemonDatabase.pokemonDao()
    }
}