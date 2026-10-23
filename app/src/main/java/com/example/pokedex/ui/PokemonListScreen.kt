package com.example.pokedex.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.samplePokemon
import com.example.pokedex.ui.theme.PokedexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(pokemon: List<Pokemon>) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Pokédex") }) }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(pokemon, key = { it.id }) { entry ->
                PokemonRow(entry)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Pokédex") }) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorScreen(message: String) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Pokédex") }) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(message, modifier = Modifier.padding(24.dp))
        }
    }
}

@Composable
private fun PokemonRow(pokemon: Pokemon) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "#%03d".format(pokemon.id),
                modifier = Modifier.padding(end = 12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(pokemon.name, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonListScreenPreview() {
    PokedexTheme {
        PokemonListScreen(samplePokemon)
    }
}
