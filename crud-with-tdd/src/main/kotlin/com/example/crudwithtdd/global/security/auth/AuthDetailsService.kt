package com.example.crudwithtdd.global.security.auth

import com.example.crudwithtdd.domain.user.domain.UserRepository
import com.example.crudwithtdd.domain.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByAccountId(username) ?: throw UserNotFoundException
        return AuthDetails(user)
    }
}
