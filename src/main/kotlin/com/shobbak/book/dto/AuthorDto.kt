package com.shobbak.book.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class AuthorDto(
    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @field:Email(regexp = ".*@.*")
    val name: String,
    @field:Min(value = 18, message = "Author must be at least 18 years old")
    val age: Int,
    val id: Long = 10,
    val books: List<BookDto>? = mutableListOf()
)
