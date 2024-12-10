package com.shobbak.book.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "authors")
class Author: BaseEntity() {
    @Column(nullable = false)
    var name: String? = null
    @Column(nullable = false)
    var age: Int? = null
}