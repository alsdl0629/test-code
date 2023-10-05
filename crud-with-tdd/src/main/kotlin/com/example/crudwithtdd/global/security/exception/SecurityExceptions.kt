package com.example.crudwithtdd.global.security.exception

import com.example.crudwithtdd.global.error.CustomException

object ExpiredTokenException : CustomException(401, "만료된 JWT입니다.")

object InvalidTokenException : CustomException(401, "잘못된 JWT 토큰입니다.")
