package com.shobbak.book.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.PreRemove
import jakarta.persistence.Table

@Entity
@Table(name = "books")
class Book: BaseEntity() {
    var name: String? = null
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    var author: Author? = null

    @PreRemove
    fun preRemove() {
        println("pre remove")
    }
}