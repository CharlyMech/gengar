package com.charlymech.gengar.controller;

import com.charlymech.gengar.model.pokemon.Pokemon
import com.charlymech.gengar.model.pokemon.PokemonAPI
import com.charlymech.gengar.model.pokemon.PokemonSpeciesAPI
import com.charlymech.gengar.service.PokemonService
import com.charlymech.gengar.utils.fromApiToPokemon
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
@RequestMapping("/pokemon")
public class PokemonController(private val pokemonService: PokemonService) {
	@GetMapping("/")
	fun getRandomPokemon() = runBlocking {
		return@runBlocking try {
			val pokemonCount: Int = pokemonService.getPokemonCount()
			val randomPokemonId: Int = Random.nextInt(0, pokemonCount)
			val pokemonAPI: PokemonAPI = pokemonService.getPokemonById(randomPokemonId)
			val pokemonSpeciesAPI: PokemonSpeciesAPI = pokemonService.getPokemonSpeciesById(randomPokemonId)
			val result: Pokemon = fromApiToPokemon(pokemonAPI, pokemonSpeciesAPI)

			ResponseEntity(Json.encodeToString(result), HttpStatus.OK)
		} catch (e: Exception) {
			ResponseEntity("Error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
		}
	}
	@GetMapping("/{id}")
	fun getPokemonById(@PathVariable id: Int) = runBlocking {
		return@runBlocking try {
			val pokemonAPI: PokemonAPI = pokemonService.getPokemonById(id)
			val pokemonSpeciesAPI: PokemonSpeciesAPI = pokemonService.getPokemonSpeciesById(id)
			val result: Pokemon = fromApiToPokemon(pokemonAPI, pokemonSpeciesAPI)

			ResponseEntity(Json.encodeToString(result), HttpStatus.OK)
		} catch (e: Exception) {
			ResponseEntity("Error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
		}
	}
}
