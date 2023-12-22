package com.vt.avowsgardaortotest.data

import android.util.Log
import com.vt.avowsgardaortotest.data.dto.DetailsPokemonResponse
import com.vt.avowsgardaortotest.data.dto.ResultsItem
import com.vt.avowsgardaortotest.data.remote.ApiService
import com.vt.avowsgardaortotest.data.remote.network.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun getAllPokemon(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getPokemon()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(ioDispatcher)
    }

    fun getDetailsPokemon(name: String): Flow<ApiResponse<DetailsPokemonResponse>> {
        return flow {
            try {
                val response = apiService.getDetailsPokemon(name)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(ioDispatcher)
    }
}