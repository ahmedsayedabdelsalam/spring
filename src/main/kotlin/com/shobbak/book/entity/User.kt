package com.shobbak.book.entity

import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User: BaseEntity(){

    @Column(name = "name" , nullable = false)
    var name: String? = null
    @Column(name = "email" , unique = true, nullable = false)
    var email: String? = null

    @Column(name = "password" , nullable = false)
    var password: String? = null

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val roles: Set<UserRole> = setOf(UserRole.ROLE_USER)
}