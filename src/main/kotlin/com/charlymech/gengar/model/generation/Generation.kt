package com.charlymech.gengar.model.generation

import com.charlymech.gengar.model.pokemon.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class Generation(
	val id: Int,
	val name: String,
	val mainRegionName: String,
//    val pokemonList: List<Pokemon>
)