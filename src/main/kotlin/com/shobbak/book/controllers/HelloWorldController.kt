package com.shobbak.book.controllers

import com.shobbak.book.entity.Author
import com.shobbak.book.repos.AuthorRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class HelloWorldController(var authorRepo: AuthorRepo) {
    companion object {
        const val HELLO_WORLD = "Hello World!"
    }

    @GetMapping("/hello")
    fun helloWorld() = HELLO_WORLD

    @GetMapping("")
    fun saveAuthor(): MutableList<Author> {
        var author = Author()
        author.name = "ahmed"
        author.age = 23
        authorRepo.save(author)
        author = authorRepo.findAll().first()
        author.createdAt = LocalDateTime.now().plusYears(1)
        authorRepo.save(author)
        return authorRepo.findAll()
    }

    @GetMapping("/update")
    fun updateAuthor(): MutableList<Author> {
        var author = authorRepo.findAll().first()
        author.name = "shobbak"
        authorRepo.save(author)
        return authorRepo.findAll()
    }
}