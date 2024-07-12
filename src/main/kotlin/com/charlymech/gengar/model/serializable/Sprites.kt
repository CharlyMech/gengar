package com.charlymech.gengar.model.serializable

import kotlinx.serialization.Serializable

@Serializable
data class Sprites(
	val default: String?,
	val back_default: String?,
	val back_female: String?,
	val back_shiny: String?,
	val back_shiny_female: String?,
	val front_default: String?,
	val front_female: String?,
	val front_shiny: String?,
	val front_shiny_female: String?,
	val other: SpritesOther?,

)

@Serializable
data class SpritesOther (
	val dream_world: SpritesDreamWorld,
	val home: SpritesHome,
	val official_artwork: SpritesOfficialArtWork, //! This might crash
	val showdown: SpritesShowdown
)

@Serializable
data class SpritesDreamWorld(
	val front_default: String?,
	val front_female: String?
)

@Serializable
data class SpritesHome(
	val front_default: String?,
	val front_female: String?,
	val front_shiny: String?,
	val front_shiny_female: String?
)

@Serializable
data class SpritesOfficialArtWork(
	val front_default: String?,
	val front_female: String?
)

@Serializable
data class SpritesShowdown(
	val back_default: String?,
	val back_female: String?,
	val back_shiny: String?,
	val back_shiny_female: String?,
	val front_default: String?,
	val front_female: String?,
	val front_shiny: String?,
	val front_shiny_female: String?,
)