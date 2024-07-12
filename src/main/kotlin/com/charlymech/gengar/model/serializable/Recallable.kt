package com.charlymech.gengar.model.serializable

import kotlinx.serialization.Serializable

@Serializable
data class Recallable(
	val name: String,
	val url: String
)
