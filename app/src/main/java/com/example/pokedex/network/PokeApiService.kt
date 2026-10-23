package com.example.pokedex.network

import com.example.pokedex.network.dto.PokemonListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0
    ): PokemonListResponseDto
}
