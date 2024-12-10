package com.shobbak.book.entity

import com.shobbak.book.observers.BaseEntityObserver
import jakarta.persistence.*
import java.time.LocalDateTime

@EntityListeners(BaseEntityObserver::class)
@MappedSuperclass
class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(updatable = false, nullable = false)
    var createdAt: LocalDateTime? = null
    @Column(nullable = false)
    var updatedAt: LocalDateTime? = null
}