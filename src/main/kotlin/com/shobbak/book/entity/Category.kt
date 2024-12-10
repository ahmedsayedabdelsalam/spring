package com.shobbak.book.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "categories")
class Category: BaseEntity() {
}