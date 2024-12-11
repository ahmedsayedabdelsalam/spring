package com.shobbak.book.controllers

import com.shobbak.book.dto.LoginDto
import com.shobbak.book.dto.RegisterDto
import com.shobbak.book.mapper.UserMapper
import com.shobbak.book.repos.UserRepo
import com.shobbak.book.utilts.Jwt
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    val userRepo: UserRepo,
    val userMapper: UserMapper,
    val passwordEncoder: PasswordEncoder,
    val authenticationManager: AuthenticationManager,
    val jwt: Jwt
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody registerDto: RegisterDto): ResponseEntity<RegisterDto> {
        val user = userMapper.toEntity(registerDto)
        user.password = passwordEncoder.encode(user.password)
        val savedUser = userRepo.save(user)
        return ResponseEntity.ok(userMapper.toDto(savedUser))
    }


    @PostMapping("/login")
    fun register(@Valid @RequestBody loginDto: LoginDto): ResponseEntity<String> {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginDto.email, loginDto.password))
        val token = jwt.generate(loginDto.email)
        return ResponseEntity.ok(token)
    }
}