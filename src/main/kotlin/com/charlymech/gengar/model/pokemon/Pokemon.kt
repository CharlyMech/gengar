package com.charlymech.gengar.model.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val officialArtWorkUrl: String?,
    val weight: Double,
    val height: Double,
//    val pokemonColor,
//    val types: List<PokemonTypes>,
//    val initialAbilities,
//    val moves: List<Moves>,
//    val pokemonGif: String,
//    val pokemonCry: String,
    // look for more assets
    // STATS!!
)
