package com.example.crudwithtdd.domain.user.domain

enum class Authority(
    val description: String,
) {
    USER("유저"),
    ADMIN("어드민"),
}
