package com.shobbak.book.annotation

import com.shobbak.book.validator.UniqueEmailValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueEmailValidator::class])
annotation class UniqueEmail(
    val message: String = "Email already exists",
    val entity: String,
    val field: String = "email",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []

) {


}