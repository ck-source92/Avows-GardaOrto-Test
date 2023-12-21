package com.vt.avowsgardaortotest.data.remote

import com.vt.avowsgardaortotest.data.dto.DetailsPokemonResponse
import com.vt.avowsgardaortotest.data.dto.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/v2/pokemon/")
    suspend fun getPokemon(): PokemonResponse

    @GET("api/v2/pokemon/1/")
    suspend fun getDetailsPokemon(): Response<DetailsPokemonResponse>
}