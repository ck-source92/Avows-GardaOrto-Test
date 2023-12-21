package com.vt.avowsgardaortotest.data.remote

import com.vt.avowsgardaortotest.data.dto.DetailsPokemonResponse
import com.vt.avowsgardaortotest.data.dto.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/v2/pokemon/")
    suspend fun getPokemon(): PokemonResponse

    @GET("api/v2/pokemon/{name}")
    suspend fun getDetailsPokemon(
        @Path("name") name: String
    ): DetailsPokemonResponse
}