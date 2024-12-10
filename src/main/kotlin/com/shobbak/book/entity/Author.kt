package com.shobbak.book.entity

import jakarta.persistence.*

@Entity
@Table(name = "authors")
class Author: BaseEntity() {
    @Column(nullable = false)
    var name: String? = null
    @Column(nullable = false)
    var age: Int? = null
    @OneToMany(
        mappedBy = "author",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    var books: MutableList<Book> = mutableListOf()

    fun addBook(book: Book) {
        books.add(book)
        book.author = this
    }

    fun removeBook(book: Book) {
        books.remove(book)
        book.author = null
    }

}