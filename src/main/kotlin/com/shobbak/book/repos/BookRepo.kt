package com.shobbak.book.repos

import com.shobbak.book.entity.Author
import com.shobbak.book.entity.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepo : JpaRepository<Book, Long> {
}