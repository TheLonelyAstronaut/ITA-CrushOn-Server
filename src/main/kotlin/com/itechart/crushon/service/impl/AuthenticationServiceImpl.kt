package com.itechart.crushon.service.impl

import com.itechart.crushon.dto.authentication.AuthenticationOutputDTO
import com.itechart.crushon.service.AuthenticationService
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl: AuthenticationService {
    override fun authenticate(username: String, password: String): AuthenticationOutputDTO {
        println(username == "test")
        println(password == "123")
        if(username == "test" && password == "123") {
            return AuthenticationOutputDTO("authToken", "userData");
        } else {
            throw Exception("Not authorized")
        }
    }
}
