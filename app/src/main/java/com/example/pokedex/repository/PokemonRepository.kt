package com.example.pokedex.repository

import com.example.pokedex.model.Pokemon
import com.example.pokedex.network.NetworkModule
import com.example.pokedex.network.PokeApiService

private const val GEN_1_COUNT = 151

class PokemonRepository(
    private val api: PokeApiService = NetworkModule.pokeApiService
) {
    suspend fun getPokemonList(): List<Pokemon> {
        val response = api.getPokemonList(limit = GEN_1_COUNT)
        return response.results.map { item ->
            Pokemon(
                id = item.url.trimEnd('/').substringAfterLast('/').toInt(),
                name = item.name.replaceFirstChar { it.uppercase() }
            )
        }.sortedBy { it.id }
    }
}
