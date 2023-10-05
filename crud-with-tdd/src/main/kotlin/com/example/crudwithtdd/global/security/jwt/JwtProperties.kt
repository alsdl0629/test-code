package com.example.crudwithtdd.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val secretKey: String,
    val accessExp: Int,
) {
    companion object {
        const val HEADER = "Authorization"
        const val PREFIX = "Bearer "
        const val AUTHORITY_KEY = "auth"
    }
}