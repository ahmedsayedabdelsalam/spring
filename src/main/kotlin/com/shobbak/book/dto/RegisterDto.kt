package com.shobbak.book.dto

import com.shobbak.book.annotation.UniqueEmail
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank


data class RegisterDto(
    @Email
    @UniqueEmail(
        entity = "User",
        field = "email"
    )
    @NotBlank
    var email: String? = null,
    @NotBlank
    var password: String? = null,
    @NotBlank
    var name: String? = null
) {

}
