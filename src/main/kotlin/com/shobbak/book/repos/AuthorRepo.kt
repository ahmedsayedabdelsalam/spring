package com.shobbak.book.repos

import com.shobbak.book.entity.Author
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AuthorRepo : JpaRepository<Author, Long> {
    /**
     * Retrieves a list of authors with the specified age.
     *
     * @param age the age of the authors to retrieve
     * @return a mutable list of authors with the given age
     */
    fun age(age: Int): MutableList<Author>


    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books")
    fun fetchAuthorsWithBooks(pageable: Pageable): Page<Author>


    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books")
    fun fetchAuthorsWithBooks(): MutableList<Author>

}