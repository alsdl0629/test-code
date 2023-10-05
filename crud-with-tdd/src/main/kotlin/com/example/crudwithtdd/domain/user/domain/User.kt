package com.example.crudwithtdd.domain.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @field:NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    val accountId: String,
    @field:NotNull
    @Column(columnDefinition = "CHAR(60)")
    val password: String,
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(5)")
    val authority: Authority,
)
