package com.shobbak.book.middleware

import com.shobbak.book.UserDetailService
import com.shobbak.book.repos.UserRepo
import com.shobbak.book.utilts.Jwt
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter


@Configuration
class JwtAuthentication(
    val jwt: Jwt,
    val userDetailsService: UserDetailService,
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")?.substringAfter("Bearer ")

        if (token == null) {
            filterChain.doFilter(request, response)
            return
        }

        val email = jwt.claim(token)

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(email)
        val authenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authenticationToken

        filterChain.doFilter(request, response)
    }
}