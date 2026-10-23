package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedex.ui.ErrorScreen
import com.example.pokedex.ui.LoadingScreen
import com.example.pokedex.ui.PokemonListScreen
import com.example.pokedex.ui.theme.PokedexTheme
import com.example.pokedex.viewmodel.PokemonListUiState
import com.example.pokedex.viewmodel.PokemonListViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                when (val current = state) {
                    is PokemonListUiState.Loading -> LoadingScreen()
                    is PokemonListUiState.Error -> ErrorScreen(current.message)
                    is PokemonListUiState.Success -> PokemonListScreen(current.pokemon)
                }
            }
        }
    }
}
