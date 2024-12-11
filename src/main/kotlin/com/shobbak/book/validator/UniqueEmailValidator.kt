package com.shobbak.book.validator

import com.shobbak.book.annotation.UniqueEmail
import jakarta.persistence.EntityManager
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.stereotype.Component

class UniqueEmailValidator(
    private val entityManager: EntityManager,
    private var entity: String? = null,
    private var field: String? = null

) : ConstraintValidator<UniqueEmail, String> {


    override fun initialize(uniqueEmail: UniqueEmail) {
        entity = uniqueEmail.entity
        field = uniqueEmail.field
    }

    override fun isValid(email: String?, context: ConstraintValidatorContext?): Boolean {
        if (email == null) return true

        val query = "SELECT 1 FROM $entity WHERE $field = :email"
        val result = entityManager.createQuery(query).setParameter("email", email).resultList

        if (result.isEmpty()) return true

        return false

    }

}
