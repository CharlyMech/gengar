package com.charlymech.gengar.model.pokemon

import com.charlymech.gengar.model.serializable.Recallable
import com.charlymech.gengar.model.serializable.Sprites
import kotlinx.serialization.Serializable

@Serializable
data class PokemonAPI(
	val abilities: List<Ability>,
	val base_experience: Int,
	val cries: Cries,
	val forms: List<Recallable>,
	val game_indeces: List<GameIndex>,
	val height: Int,
	val held_items: List<HeldItem>,
	val id: Int,
	val is_default: Boolean,
	val location_area_encounters: String,
	val moves: Move,
	val name: String,
	val order: Int,
	val past_abilities: Recallable, //! This might crash!
	val past_types: Recallable, //! This might crash!
	val species: Recallable,
	val sprites: Sprites,
	val stats: List<Stat>,
	val types: List<PokemonType>,
	val weight: Int
)

@Serializable
data class Ability(
	val ability: Recallable,
	val is_hidden: Boolean,
	val slot: Int
)

@Serializable
data class Cries(
	val latest: String,
	val legacy: String
)

@Serializable
data class GameIndex(
	val game_index: Int,
	val version: Recallable
)

@Serializable
data class HeldItem(
	val item: Recallable,
	val version_details: List<Recallable>
)

@Serializable
data class Move(
	val move: Recallable,
	val version_group_details: List<VersionGroupDetail>
)

@Serializable
data class VersionGroupDetail(
	val level_learned_at: Int,
	val move_learn_method: Recallable,
	val version_group: Recallable
)

@Serializable
data class Stat(
	val base_stat: Int,
	val effort: Int,
	val stat: Recallable
)

@Serializable
data class PokemonType(
	val slot: Int,
	val type: Recallable
)