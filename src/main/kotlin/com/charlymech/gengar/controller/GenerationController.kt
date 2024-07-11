package com.charlymech.gengar.controller

import com.charlymech.gengar.model.generation.Generation
import com.charlymech.gengar.model.generation.GenerationAPI
import com.charlymech.gengar.service.GenerationService
import com.charlymech.gengar.utils.fromApiToGeneration
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/generation")
class GenerationController(private val generationService: GenerationService) {
	@GetMapping("/all")
	fun getAllGenerations() = runBlocking {
		return@runBlocking try {
			val result: List<GenerationAPI> = generationService.getAllGenerations()
			val generationsList = result
				.map{ genApi -> fromApiToGeneration(genApi) }
			val generationsJsonList = Json.encodeToString(generationsList)
			ResponseEntity(generationsJsonList, HttpStatus.OK)
		} catch (e: Exception) {
			ResponseEntity("Error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
		}
	}

	@GetMapping("/{id}")
	fun getGeneration(@PathVariable id: Int) = runBlocking {
		return@runBlocking try {
			val result: Generation = fromApiToGeneration(generationService.getGeneration(id))
			ResponseEntity(Json.encodeToString(result), HttpStatus.OK)
		} catch (e: Exception) {
			ResponseEntity("Error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
		}
	}
}