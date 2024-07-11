package com.charlymech.gengar.utils

import com.charlymech.gengar.model.generation.Generation
import com.charlymech.gengar.model.generation.GenerationAPI

fun fromApiToGeneration(generationAPI: GenerationAPI): Generation {
	return Generation(
		id = generationAPI.id,
		name = formatGenerationName(generationAPI.name),
		mainRegionName = generationAPI.main_region["name"]!!.capitalize()
		// TODO -> Implement rest of properties
	)
}

fun formatGenerationName(name: String): String {
	// All names follow this regex {generation-xx} where 'xx' are Roman numbers
	val splitName: List<String> = name.split("-")
	val genName: String = splitName[0].capitalize()
	val genNumber: String = splitName[1].toUpperCase()
	return "$genName $genNumber"
}