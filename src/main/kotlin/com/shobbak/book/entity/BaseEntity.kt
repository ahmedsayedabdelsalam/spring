package com.shobbak.book.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime

//@EntityListeners(BaseEntityObserver::class)
@MappedSuperclass
class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    var createdAt: ZonedDateTime? = null
    @Column(nullable = false)
    @LastModifiedDate
    @CreationTimestamp
    var updatedAt: ZonedDateTime? = null
}