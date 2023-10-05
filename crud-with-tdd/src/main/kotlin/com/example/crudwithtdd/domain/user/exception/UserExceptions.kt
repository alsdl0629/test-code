package com.example.crudwithtdd.domain.user.exception

import com.example.crudwithtdd.global.error.CustomException

object UserNotFoundException : CustomException(404, "유저를 찾지 못함")
