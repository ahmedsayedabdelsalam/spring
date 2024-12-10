package com.shobbak.book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import com.shobbak.book.controllers.HelloWorldController

@SpringBootApplication
class BookApplication

fun main(args: Array<String>) {
    runApplication<BookApplication>(*args)
    println(HelloWorldController.HELLO_WORLD)
}
