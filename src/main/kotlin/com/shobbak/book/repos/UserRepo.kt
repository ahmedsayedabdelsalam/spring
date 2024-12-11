package com.shobbak.book.repos

import com.shobbak.book.entity.Author
import com.shobbak.book.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepo : JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    fun existsByEmail(email: String): Boolean

    @Query("SELECT u FROM User u WHERE u.email = :email")
    fun findByEmail(email: String): User
}