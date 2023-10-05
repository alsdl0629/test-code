package com.example.crudwithtdd.global.security.jwt

import com.example.crudwithtdd.global.exception.InternalServerErrorException
import com.example.crudwithtdd.global.security.auth.AuthDetailsService
import com.example.crudwithtdd.global.security.exception.ExpiredTokenException
import com.example.crudwithtdd.global.security.exception.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtParser(
    private val authDetailsService: AuthDetailsService,
    private val jwtProperties: JwtProperties,
) {
    fun getAuthentication(token: String): Authentication {
        val claims =
            getClaims(token).apply {
                if (this.header[Header.JWT_TYPE] != JwtConstant.ACCESS) {
                    throw InvalidTokenException
                }
            }

        val userDetails = authDetailsService.loadUserByUsername(claims.body.subject)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parser()
                .setSigningKey(jwtProperties.secretKey)
                .parseClaimsJws(token)
        } catch (exception: Exception) {
            when (exception) {
                is ExpiredJwtException -> throw ExpiredTokenException
                is JwtException -> throw InvalidTokenException
                else -> throw InternalServerErrorException
            }
        }
    }
}
