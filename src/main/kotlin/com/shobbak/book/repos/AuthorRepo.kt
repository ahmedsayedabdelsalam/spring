package com.shobbak.book.repos

import com.shobbak.book.entity.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepo : JpaRepository<Author, Long> {
    fun age(age: Int): MutableList<Author>
}