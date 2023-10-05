package com.example.crudwithtdd.global.error

abstract class CustomException(
    val status: Int,
    override val message: String,
) : RuntimeException()
