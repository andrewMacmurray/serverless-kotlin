package com.example.sls

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): String = "Hello World!"

    @GetMapping("/message")
    fun message() = Message("I LOVE BREAD!")
}

data class Message(val message: String)