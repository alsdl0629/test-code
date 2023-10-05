package com.example.crudwithtdd.domain.user.domain

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByAccountId(accountId: String): User?
}
