package com.shobbak.book.controllers

import com.shobbak.book.dto.AuthorDto
import com.shobbak.book.dto.BookDto
import com.shobbak.book.entity.Author
import com.shobbak.book.entity.Book
import com.shobbak.book.entity.Category
import com.shobbak.book.repos.AuthorRepo
import com.shobbak.book.repos.BookRepo
import com.shobbak.book.repos.CategoryRepo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class HelloWorldController(var authorRepo: AuthorRepo, val categoryRepo: CategoryRepo, val bookRepo: BookRepo) {
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