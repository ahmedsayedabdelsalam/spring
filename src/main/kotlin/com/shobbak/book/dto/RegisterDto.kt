package com.shobbak.book.dto

import com.shobbak.book.annotation.UniqueEmail
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull


data class RegisterDto(
    @field:UniqueEmail(
        entity = "User",
        field = "email"
    )
    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(regexp = ".*@.*")
    var email: String? = null,
    @field:NotBlank(message = "Password cannot be blank")
    var password: String? = null,
    @field:NotBlank(message = "Name cannot be empty")
    var name: String? = null
) {

}
