package com.charlymech.gengar.service

import com.charlymech.gengar.App.Companion.BASE_URL
import com.charlymech.gengar.model.generation.GenerationAPI
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Service
class GenerationService {
	private val httpClient: OkHttpClient = OkHttpClient();
	private val generationUrl: String = "$BASE_URL/generation/";

	suspend fun getAllGenerations(): List<GenerationAPI> = coroutineScope {
		val ids: List<Int> = getGenerationIds()
		val deferredResults = ids.map { id ->
			async {
				getGeneration(id)
			}
		}
		try {
			deferredResults.awaitAll()
		} catch (e: Exception) {
			throw RuntimeException("An error occurred while fetching generation data: ${e.message}", e)
		}
	}

	private suspend fun getGenerationIds(): List<Int> = suspendCancellableCoroutine { continuation ->
		val request = Request.Builder()
			.url(generationUrl)
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
							val jsonList = JSONObject(response.body!!.string()).optJSONArray("results");
							val generationsIdsList: List<Int> = jsonList.map { gen ->
								JSONObject(gen.toString()).getString("url").replace("/", "").last().toString().toInt()
							}
							continuation.resume(generationsIdsList)
						}
					}
				}
			}
		})

		continuation.invokeOnCancellation {
			httpClient.dispatcher.executorService.shutdown()
		}
	}

	suspend fun getGeneration(id: Int): GenerationAPI {
		return suspendCancellableCoroutine { continuation ->
			val request = Request.Builder()
				.url("$generationUrl/$id")
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
								val generationString: String = JSONObject(response.body!!.string()).toString()
								val generation: GenerationAPI = Json.decodeFromString(generationString)
								continuation.resume(generation)
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