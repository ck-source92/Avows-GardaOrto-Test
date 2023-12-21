package com.vt.avowsgardaortotest.utils

import com.vt.avowsgardaortotest.data.domain.model.Pokemon
import com.vt.avowsgardaortotest.data.dto.ResultsItem
import com.vt.avowsgardaortotest.data.local.entity.PokemonEntity

object DataMapper {
    fun mapResponseToEntity(input: List<ResultsItem>): List<PokemonEntity> {
        val pokemonEntity = ArrayList<PokemonEntity>()
        input.map {
            val pokemon = PokemonEntity(
                url = it.url,
                name = it.name, id = null
            )
            pokemonEntity.add(pokemon)
        }
        return pokemonEntity
    }

    fun mapEntitiesToDomain(input: List<PokemonEntity>): List<Pokemon> =
        input.map {
            Pokemon(
                url = it.url,
                name = it.name,
                id = it.id!!
            )
        }
}