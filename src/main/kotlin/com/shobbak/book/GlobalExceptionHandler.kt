package com.shobbak.book

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import jakarta.validation.ValidationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    // Handle Bean Validation errors
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<MutableMap<String, String>> {
        val errorsHash = mutableMapOf<String, String>()
        ex.bindingResult
            .fieldErrors
            .forEach {
                println(it)
                errorsHash[it.field] = it.defaultMessage!!
            }

        return ResponseEntity(errorsHash, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(JwtException::class)
    fun handleExpiredJWTException(ex: JwtException): ResponseEntity<String> {
       return ResponseEntity("Invalid Token", HttpStatus.UNAUTHORIZED)
    }

}