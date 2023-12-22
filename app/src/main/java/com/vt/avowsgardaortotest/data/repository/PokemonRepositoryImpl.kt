package com.vt.avowsgardaortotest.data.repository

import com.vt.avowsgardaortotest.data.LocalDataSource
import com.vt.avowsgardaortotest.data.NetworkBoundResource
import com.vt.avowsgardaortotest.data.RemoteDataSource
import com.vt.avowsgardaortotest.data.domain.model.Pokemon
import com.vt.avowsgardaortotest.data.domain.repository.IPokemonRepository
import com.vt.avowsgardaortotest.data.dto.DetailsPokemonResponse
import com.vt.avowsgardaortotest.data.dto.ResultsItem
import com.vt.avowsgardaortotest.data.remote.network.ApiResponse
import com.vt.avowsgardaortotest.data.remote.network.Resource
import com.vt.avowsgardaortotest.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IPokemonRepository {
    override fun getAllPokemon(): Flow<Resource<List<Pokemon>>> =
        object : NetworkBoundResource<List<Pokemon>, List<ResultsItem>>() {
            override fun loadFromDatabase(): Flow<List<Pokemon>> {
                return localDataSource.getAllPokemon().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Pokemon>?): Boolean = true
            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getAllPokemon()
            }

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val pokemonEntity = DataMapper.mapResponseToEntity(data)
                localDataSource.insertPokemonToLocal(pokemonEntity)
            }
        }.asFlow()


    override fun getOfflinePokemons(): Flow<List<Pokemon>> {
        return localDataSource.getOfflinePokemon().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun searchPokemon(query: String): Flow<List<Pokemon>> {
        return localDataSource.searchPokemon(query).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun detailsPokemon(name: String): Flow<Resource<DetailsPokemonResponse>> {
        return remoteDataSource.getDetailsPokemon(name)
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        // Success case: Convert to Resource.Success
                        Resource.Success(apiResponse.data)
                    }

                    is ApiResponse.Error -> {
                        // Error case: Convert to Resource.Error
                        Resource.Error(apiResponse.errorMessage)
                    }

                    ApiResponse.Empty -> TODO()
                }
            }
            .onStart {
                // You can emit a loading state if needed
                emit(Resource.Loading(null))
            }
    }
}