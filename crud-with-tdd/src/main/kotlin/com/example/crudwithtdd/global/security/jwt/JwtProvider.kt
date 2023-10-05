package com.example.crudwithtdd.global.security.jwt

import com.example.crudwithtdd.domain.user.domain.Authority
import com.example.crudwithtdd.global.security.auth.AuthDetailsService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtProvider(
    private val authDetailsService: AuthDetailsService,
    private val jwtProperties: JwtProperties,
) {
    fun generateToken(
        id: String,
        authority: Authority,
    ) = Jwts.builder()
        .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
        .setSubject(id)
        .claim(JwtProperties.AUTHORITY_KEY, authority)
        .setIssuedAt(Date())
        .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp * 1000))
        .compact()
}
