package com.example.pokedex.model

data class Pokemon(
    val id: Int,
    val name: String
)

val samplePokemon = listOf(
    Pokemon(1, "Bulbasaur"),
    Pokemon(4, "Charmander"),
    Pokemon(7, "Squirtle"),
    Pokemon(25, "Pikachu")
)
