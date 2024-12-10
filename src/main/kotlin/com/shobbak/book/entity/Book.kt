package com.shobbak.book.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*;

@Entity
@Table(name = "books")
class Book : BaseEntity() {
    var name: String? = null

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    var author: Author? = null

    @ManyToMany()
    @JoinTable(
        name = "book_category",
        joinColumns = [JoinColumn(name = "book_id", nullable = false)],
        inverseJoinColumns = [JoinColumn(name = "category_id", nullable = false)]
    )
    var categories: MutableList<Category> = mutableListOf()

    @PreRemove
    fun preRemove() {
        println("pre remove")
    }
}