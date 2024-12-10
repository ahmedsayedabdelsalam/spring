package com.shobbak.book.middleware

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

//@Component
class Authentication: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if(request.requestURI.contains("login")){
            filterChain.doFilter(request, response)
        } else {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("Unauthorized")
        }
    }
}