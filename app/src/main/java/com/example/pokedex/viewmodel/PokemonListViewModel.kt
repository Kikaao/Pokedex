package com.example.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.repository.PokemonRepository
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PokemonListViewModel(
    private val repository: PokemonRepository = PokemonRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Loading)
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    init {
        loadPokemon()
    }

    fun loadPokemon() {
        viewModelScope.launch {
            _uiState.value = PokemonListUiState.Loading
            _uiState.value = try {
                PokemonListUiState.Success(repository.getPokemonList())
            } catch (e: IOException) {
                PokemonListUiState.Error("Couldn't reach PokéAPI. Check your connection.")
            } catch (e: HttpException) {
                PokemonListUiState.Error("PokéAPI error: ${e.code()}")
            }
        }
    }
}
