package com.charlymech.gengar.model.pokemon

import com.charlymech.gengar.model.serializable.Recallable
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesAPI(
	val base_happiness: Int,
	val capture_rate: Int,
	val color: Map<String, String>, // Improve? Colors constant or sth
	val egg_groups: List<Map<String, String>>,
	val evolution_chain: Map<String, String>,
	val evolves_from_species: Map<String, String>?,
	val flavor_text_entries: List<TextEntry>,
	val form_descriptions: List<Form>,
	val forms_switchable: Boolean,
	val gender_rate: Int,
	val genera: List<Genera>,
	val generation: Recallable,
	val growth_rate: Recallable,
	val habitat: Recallable,
	val has_gender_differences: Boolean,
	val hatch_counter: Int,
	val id: Int,
	val is_baby: Boolean,
	val is_legendary: Boolean,
	val is_mythical: Boolean,
	val name: String,
	val order: Int,
	val pal_park_encounters: List<ParkEncounters>,
	val shape: Recallable,
	val varieties: List<Variety>
)

@Serializable
data class TextEntry (
	val flavor_text: String,
	val language: Recallable,
	val version: Recallable
)

@Serializable
data class Form (
	val description: String,
	val language: Recallable,
)

@Serializable
data class Genera (
	val genus: String,
	val language: Recallable,
)

@Serializable
data class ParkEncounters (
	val area: Recallable,
	val base_score: Int,
	val rate: Int
)

@Serializable
data class Variety (
	val is_default: Boolean,
	val pokemon: Recallable
)