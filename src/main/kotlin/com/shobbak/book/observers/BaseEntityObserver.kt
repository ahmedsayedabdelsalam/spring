package com.shobbak.book.observers

import com.shobbak.book.entity.BaseEntity
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.ZonedDateTime

class BaseEntityObserver {
    @PrePersist
    fun prePersist(target: BaseEntity) {
        target.createdAt = ZonedDateTime.now()
        target.updatedAt = target.createdAt
        println("Author Persisted")
    }

    @PreUpdate
    fun preUpdate(target: BaseEntity) {
        target.updatedAt = ZonedDateTime.now()
        println("Author Updated")
    }
}