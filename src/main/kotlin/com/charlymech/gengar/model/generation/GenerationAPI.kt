package com.charlymech.gengar.model.generation

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class GenerationAPI (
	val id: Int,
	val name: String,
	val names: List<NameEntry>,
	val abilities: List<Map<String, String>>,
	val main_region: Map<String, String>,
	val moves: List<Map<String, String>>,
	val pokemon_species: List<Map<String, String>>,
	val types: List<Map<String, String>>,
	val version_groups: List<Map<String, String>>
)

@Serializable
data class NameEntry (
	val name: String,
	val language: Map<String, String>
)