package com.shobbak.book.dto


data class AuthorDto(
    val name: String,
    val age: Int,
    val id: Long,
    val books: List<BookDto>
)
