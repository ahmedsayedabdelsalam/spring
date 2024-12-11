package com.shobbak.book.utilts

import com.shobbak.book.controllers.HelloWorldController
import com.shobbak.book.controllers.HelloWorldController.Companion
import com.shobbak.book.controllers.HelloWorldController.Companion.KEY
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class Jwt(
) {
    companion object {
        lateinit var KEY: javax.crypto.SecretKey
            private set
    }

    @Value("\${jwt.secret}")
    fun setSecret(secret: String) {
        KEY = Keys.hmacShaKeyFor(secret.toByteArray())
    }


    fun generate(subject: String): String? {
        return Jwts.builder()
            .subject(subject)
            .signWith(KEY)
            .compact()
    }
}