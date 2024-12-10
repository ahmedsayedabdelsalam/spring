package com.shobbak.book.observers

import com.shobbak.book.entity.BaseEntity
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

class BaseEntityObserver {
    @PrePersist
    fun prePersist(target: BaseEntity) {
        target.createdAt = LocalDateTime.now()
        target.updatedAt = target.createdAt
    }

    @PreUpdate
    fun preUpdate(target: BaseEntity) {
        target.updatedAt = LocalDateTime.now()
    }
}