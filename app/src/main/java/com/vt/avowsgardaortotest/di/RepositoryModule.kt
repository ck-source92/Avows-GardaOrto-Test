package com.vt.avowsgardaortotest.di

import com.vt.avowsgardaortotest.data.domain.repository.IPokemonRepository
import com.vt.avowsgardaortotest.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): IPokemonRepository
}