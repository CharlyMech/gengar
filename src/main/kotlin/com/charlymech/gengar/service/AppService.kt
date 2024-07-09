package com.charlymech.gengar.service

import com.charlymech.gengar.App.Companion.BASE_URL
import okhttp3.*
import org.springframework.stereotype.Service
import java.io.IOException

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


@Service
class AppService() {
	private val httpClient: OkHttpClient = OkHttpClient();

	suspend fun pingPong(): Boolean {
		return suspendCancellableCoroutine { continuation ->
			val request = Request.Builder()
				.url(BASE_URL)
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
								continuation.resume(true)
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