package com.charlymech.gengar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.github.cdimascio.dotenv.dotenv


@SpringBootApplication
class App {
	companion object {
		val dotenv = dotenv();
		val BASE_URL: String = dotenv["POKE_API"] ?: "https://pokeapi.co/api/v2/";
	}
}

fun main(args: Array<String>) {
	runApplication<App>(*args);
}
