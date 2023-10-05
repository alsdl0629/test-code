package com.example.crudwithtdd.global.filter

import com.example.crudwithtdd.global.security.jwt.JwtConstant.HEADER
import com.example.crudwithtdd.global.security.jwt.JwtConstant.PREFIX
import com.example.crudwithtdd.global.security.jwt.JwtParser
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val jwtParser: JwtParser,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = getToken(request)
        token?.let {
            SecurityContextHolder.getContext().authentication = jwtParser.getAuthentication(token)
        }

        filterChain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest): String? {
        val token = request.getHeader(HEADER)

        return if (!token.isNullOrEmpty() && token.startsWith(PREFIX)) {
            token.substring(PREFIX.length)
        } else {
            null
        }
    }
}