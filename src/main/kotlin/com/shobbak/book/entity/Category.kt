package com.shobbak.book.entity

import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "categories")
class Category: BaseEntity() {
    var name: String? = null
    @ManyToMany(mappedBy = "categories")
    var books: MutableList<Book> = mutableListOf()
}