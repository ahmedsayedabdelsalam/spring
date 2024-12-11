package com.shobbak.book.controllers

import com.shobbak.book.dto.AuthorDto
import com.shobbak.book.entity.Author
import com.shobbak.book.entity.Book
import com.shobbak.book.entity.Category
import com.shobbak.book.mapper.AuthorMapper
import com.shobbak.book.repos.AuthorRepo
import com.shobbak.book.repos.BookRepo
import com.shobbak.book.repos.CategoryRepo
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Date


@RestController
class HelloWorldController(
    private val authorMapper: AuthorMapper,
    var authorRepo: AuthorRepo, val categoryRepo: CategoryRepo, val bookRepo: BookRepo) {
    companion object {
        const val HELLO_WORLD = "Hello World!"
        lateinit var KEY: javax.crypto.SecretKey
            private set
    }

    @Value("\${jwt.secret}")
    fun setSecret(secret: String) {
        KEY = Keys.hmacShaKeyFor(secret.toByteArray())
    }

    @GetMapping("/hello")
    fun helloWorld() = HELLO_WORLD

    @GetMapping("/login")
    fun login() = "login success"

    @GetMapping("/jwt")
    fun jwt(): String {
        println(KEY)
        return Jwts.builder()
            .claims(mutableMapOf("sub" to "test", "author" to "ahmed"))
            .issuedAt(Date())
            .expiration(Date(Date().time + 300000))
            .signWith(KEY).compact()
    }

    @GetMapping("/decode")
    fun decode(@RequestParam(name = "Authorization") authorization: String): String {
        println(KEY)
        return Jwts.parser().verifyWith(KEY).build()
            .parseSignedClaims(authorization)
            .payload["author"].toString()
    }

    @GetMapping("")
    fun saveAuthor(): MutableList<Author> {
        val user2 = authorMapper.toEntity(AuthorDto(name = "test", age = 20))
        authorRepo.save(user2)
//        var author = Author()
//        author.name = "ahmed"
//        author.age = 23
//        authorRepo.save(author)
//        author = authorRepo.findAll().first()
//        author.createdAt = LocalDateTime.now().plusYears(1)
//        authorRepo.save(author)
        return authorRepo.fetchAuthorsWithBooks()
    }

    @GetMapping("/update")
    fun updateAuthor(): MutableList<Author> {
        val author = authorRepo.findAll().first()
        author.name = "ahmed"
        authorRepo.save(author)
        return authorRepo.findAll()
    }

    @GetMapping("relation")
    fun relation(): MutableList<Author> {
        val author = Author()
        author.name = "ahmed"
        author.age = 23
        val book1 = Book()
        book1.name = "java"
        book1.author = author
        val book2 = Book()
        book2.name = "php"
        book2.author = author
        author.books.addAll(mutableSetOf(book1, book2))
        authorRepo.save(author)
        return authorRepo.findAll()
    }

    @GetMapping("/delete")
    fun deleteAuthor(): String {
        val author = authorRepo.findAll().first()
        author.books.removeAll(author.books)
        authorRepo.save(author)
        return "ok"
    }

    @GetMapping("/attach")
    fun attachCategoryToBook(): Book {
        val author = Author()
        author.name = "ahmed"
        author.age = 23

        val book1 = Book()
        book1.name = "java"
        book1.author = author

        author.books.addAll(mutableSetOf(book1))

        authorRepo.save(author)

        val book = bookRepo.findAll().first()

        val category = Category()
        category.name = "tech"

        categoryRepo.save(category)


        book.categories.add(category)
        bookRepo.save(book)


        return book

    }


    @GetMapping("/detach")
    fun detachCategoryToBook(): Book {
        val book = bookRepo.findAll().first()
        book.categories.removeAll(book.categories)
        bookRepo.save(book)
        return book
    }


    @GetMapping("/delete-book")
    fun deleteBook(): String {
        val book = bookRepo.findAll().first()
        bookRepo.delete(book)
        return "ok"
    }


//    @GetMapping("/authors")
//    fun fetchAllAuthorsWithTheirBooks(): ResponseEntity<List<AuthorDto>> {
//        var authors = authorRepo.fetchAuthorsWithBooks()
//
//        var authDataToObjects = authors.map {
//            AuthorDto(
//                name = it.name!!,
//                age = it.age!!,
//                id = it.id!!,
//                books = it.books.map { BookDto(name = it.name!!) }
//            )
//        }
//
//
//            return ResponseEntity.ok(authDataToObjects)
//    }



    @GetMapping("api/me")
    fun me(): ResponseEntity<Authentication> {
        val x = SecurityContextHolder.getContext().authentication
        return ResponseEntity.ok(x)
    }
}