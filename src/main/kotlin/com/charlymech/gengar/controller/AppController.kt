package com.charlymech.gengar.controller

import com.charlymech.gengar.service.AppService
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class AppController (private val appService: AppService) {
    @GetMapping("ping")
    fun ping(): ResponseEntity<String> = runBlocking {
        return@runBlocking try {
            val result = appService.pingPong()
            val gson: Gson = Gson()
            if (result) {
                val pingPongMap = mapOf("ping" to "pong"); //"{\"ping\":\"pong\"}"
                ResponseEntity(gson.toJson(pingPongMap).toString(), HttpStatus.OK)
            } else {
                val emptyMap = mapOf<Any, Any>()
                ResponseEntity(gson.toJson(emptyMap).toString(), HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } catch (e: Exception) {
            ResponseEntity("Error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}