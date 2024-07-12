package com.charlymech.gengar.utils

import com.charlymech.gengar.model.pokemon.Pokemon
import com.charlymech.gengar.model.pokemon.PokemonAPI
import com.charlymech.gengar.model.pokemon.PokemonSpeciesAPI

fun fromApiToPokemon(pokemonAPI: PokemonAPI, pokemonSpeciesAPI: PokemonSpeciesAPI): Pokemon {
	return Pokemon(
		id = pokemonAPI.id,
		name = pokemonAPI.name.capitalize(),
		officialArtWorkUrl = pokemonAPI.sprites.other?.official_artwork?.front_default,
		height = pokemonAPI.height.toDouble(),
		weight = pokemonAPI.weight.toDouble()
		// TODO -> Implement rest of properties
	)
}