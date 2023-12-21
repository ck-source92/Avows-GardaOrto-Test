package com.vt.avowsgardaortotest.data

import android.util.Log
import com.vt.avowsgardaortotest.data.dto.ResultsItem
import com.vt.avowsgardaortotest.data.remote.ApiService
import com.vt.avowsgardaortotest.data.remote.network.ApiResponse
import com.vt.avowsgardaortotest.data.remote.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
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
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailsPokemon() {
        val response = apiService.getDetailsPokemon()
        if (response.isSuccessful) {
            Resource.Success(response.body())
        } else {
            Resource.Error(response.message())
        }
    }
}