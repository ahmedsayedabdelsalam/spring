package com.shobbak.book.repos

import com.shobbak.book.entity.Author
import com.shobbak.book.entity.Book
import com.shobbak.book.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepo : JpaRepository<Category, Long> {
}