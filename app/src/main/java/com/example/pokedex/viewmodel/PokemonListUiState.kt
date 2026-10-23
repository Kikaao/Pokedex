package com.example.pokedex.viewmodel

import com.example.pokedex.model.Pokemon

sealed interface PokemonListUiState {
    data object Loading : PokemonListUiState
    data class Success(val pokemon: List<Pokemon>) : PokemonListUiState
    data class Error(val message: String) : PokemonListUiState
}
