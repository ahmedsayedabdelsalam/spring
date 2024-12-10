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
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.util.Date


@RestController
class HelloWorldController(
    private val authorMapper: AuthorMapper,
    var authorRepo: AuthorRepo, val categoryRepo: CategoryRepo, val bookRepo: BookRepo) {
    companion object {
        const val HELLO_WORLD = "Hello World!"
        val KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    }

    @GetMapping("/hello")
    fun helloWorld() = HELLO_WORLD

    @GetMapping("/login")
    fun login() = "login success"

    @GetMapping("/jwt")
    fun jwt(): String {
        return Jwts.builder().subject("ahmed@gmail.com")
            .issuedAt(Date())
            .expiration(Date(Date().time + 300000))
            .signWith(KEY).compact()
    }

    @GetMapping("/decode")
    fun decode(@RequestHeader(name = "Authorization") authorization: String): String {
        return Jwts.parser().verifyWith(KEY).build()
            .parseSignedClaims(authorization.substringAfter(" "))
            .payload.subject
    }

    @GetMapping("")
    fun saveAuthor(): MutableList<Author> {
        var user2 = authorMapper.toEntity(AuthorDto(name = "test", age = 20))
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
        var author = authorRepo.findAll().first()
        author.name = "shobbak"
        authorRepo.save(author)
        return authorRepo.findAll()
    }

    @GetMapping("relation")
    fun relation(): MutableList<Author> {
        var author = Author()
        author.name = "ahmed"
        author.age = 23
        var book1 = Book()
        book1.name = "java"
        book1.author = author
        var book2 = Book()
        book2.name = "php"
        book2.author = author
        author.books.addAll(mutableSetOf(book1, book2))
        authorRepo.save(author)
        return authorRepo.findAll()
    }

    @GetMapping("/delete")
    fun deleteAuthor(): String {
        var author = authorRepo.findAll().first()
        author.books.removeAll(author.books)
        authorRepo.save(author)
        return "ok"
    }

    @GetMapping("/attach")
    fun attachCategoryToBook(): Book {
        var author = Author()
        author.name = "ahmed"
        author.age = 23

        var book1 = Book()
        book1.name = "java"
        book1.author = author

        author.books.addAll(mutableSetOf(book1))

        authorRepo.save(author)

        var book = bookRepo.findAll().first()

        var category = Category()
        category.name = "tech"

        categoryRepo.save(category)


        book.categories.add(category)
        bookRepo.save(book)


        return book;

    }


    @GetMapping("/detach")
    fun detachCategoryToBook(): Book {
        var book = bookRepo.findAll().first()
        book.categories.removeAll(book.categories)
        bookRepo.save(book)
        return book
    }


    @GetMapping("/delete-book")
    fun deleteBook(): String {
        var book = bookRepo.findAll().first()
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


}