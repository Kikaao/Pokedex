package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.pokedex.model.Pokemon
import com.example.pokedex.repository.PokemonRepository
import com.example.pokedex.ui.ErrorScreen
import com.example.pokedex.ui.LoadingScreen
import com.example.pokedex.ui.PokemonListScreen
import com.example.pokedex.ui.theme.PokedexTheme
import java.io.IOException
import retrofit2.HttpException

class MainActivity : ComponentActivity() {
    private val repository = PokemonRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                var pokemonList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }
                var isLoading by remember { mutableStateOf(true) }
                var errorMessage by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(Unit) {
                    try {
                        pokemonList = repository.getPokemonList()
                    } catch (e: IOException) {
                        errorMessage = "Couldn't reach PokéAPI. Check your connection."
                    } catch (e: HttpException) {
                        errorMessage = "PokéAPI error: ${e.code()}"
                    } finally {
                        isLoading = false
                    }
                }

                when {
                    isLoading -> LoadingScreen()
                    errorMessage != null -> ErrorScreen(errorMessage!!)
                    else -> PokemonListScreen(pokemonList)
                }
            }
        }
    }
}
