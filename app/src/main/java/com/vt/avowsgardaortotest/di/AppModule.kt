package com.vt.avowsgardaortotest.di

import com.vt.avowsgardaortotest.data.domain.usecases.IPokemonUsecases
import com.vt.avowsgardaortotest.data.domain.usecases.PokemonInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideMovieUsecase(pokemonInteractor: PokemonInteractor): IPokemonUsecases
}