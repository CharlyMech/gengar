package com.charlymech.gengar.service;

import com.charlymech.gengar.App.Companion.BASE_URL
import com.charlymech.gengar.model.generation.GenerationAPI
import com.charlymech.gengar.model.pokemon.PokemonAPI
import com.charlymech.gengar.model.pokemon.PokemonSpeciesAPI
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.json.Json
import okhttp3.*
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


@Service
class PokemonService {
	private val httpClient: OkHttpClient = OkHttpClient();
	private val pokemonUrl: String = "$BASE_URL/pokemon/";
	private val pokemonSpeciesUrl: String = "$BASE_URL/pokemon-species/";

	suspend fun getPokemonCount(): Int {
		return suspendCancellableCoroutine { continuation ->
			val request = Request.Builder()
				.url(pokemonUrl)
				.build()

			httpClient.newCall(request).enqueue(object : Callback {
				override fun onFailure(call: Call, e: IOException) {
					e.printStackTrace()
					if (continuation.isActive) {
						continuation.resumeWithException(e)
					}
				}

				override fun onResponse(call: Call, response: Response) {
					response.use {
						if (!response.isSuccessful) {
							if (continuation.isActive) {
								continuation.resumeWithException(IOException("Unexpected code $response"))
							}
						} else {
							if (continuation.isActive) {
								val pokemonJson = JSONObject(response.body!!.string())

//                                val generation: GenerationAPI = Json.decodeFromString(generationString)
//                                continuation.resume(generation)
							}
						}
					}
				}
			})

			continuation.invokeOnCancellation {
				httpClient.dispatcher.executorService.shutdown()
			}
		}
	}

	suspend fun getPokemonById(id: Int): PokemonAPI {
		return suspendCancellableCoroutine { continuation ->
			val request = Request.Builder()
				.url("$pokemonUrl/$id")
				.build()

			httpClient.newCall(request).enqueue(object : Callback {
				override fun onFailure(call: Call, e: IOException) {
					e.printStackTrace()
					if (continuation.isActive) {
						continuation.resumeWithException(e)
					}
				}

				override fun onResponse(call: Call, response: Response) {
					response.use {
						if (!response.isSuccessful) {
							if (continuation.isActive) {
								continuation.resumeWithException(IOException("Unexpected code $response"))
							}
						} else {
							if (continuation.isActive) {
								val pokemonString: String = JSONObject(response.body!!.string()).toString()
								val pokemon: PokemonAPI = Json.decodeFromString(pokemonString)
								continuation.resume(pokemon)
							}
						}
					}
				}
			})

			continuation.invokeOnCancellation {
				httpClient.dispatcher.executorService.shutdown()
			}
		}
	}

	suspend fun getPokemonSpeciesById(id: Int): PokemonSpeciesAPI {
		return suspendCancellableCoroutine { continuation ->
			val request = Request.Builder()
				.url("$pokemonSpeciesUrl/$id")
				.build()

			httpClient.newCall(request).enqueue(object : Callback {
				override fun onFailure(call: Call, e: IOException) {
					e.printStackTrace()
					if (continuation.isActive) {
						continuation.resumeWithException(e)
					}
				}

				override fun onResponse(call: Call, response: Response) {
					response.use {
						if (!response.isSuccessful) {
							if (continuation.isActive) {
								continuation.resumeWithException(IOException("Unexpected code $response"))
							}
						} else {
							if (continuation.isActive) {
								val pokemonSpeciesString: String = JSONObject(response.body!!.string()).toString()
								val pokemon: PokemonSpeciesAPI = Json.decodeFromString(pokemonSpeciesString)
								continuation.resume(pokemon)
							}
						}
					}
				}
			})

			continuation.invokeOnCancellation {
				httpClient.dispatcher.executorService.shutdown()
			}
		}
	}
}
