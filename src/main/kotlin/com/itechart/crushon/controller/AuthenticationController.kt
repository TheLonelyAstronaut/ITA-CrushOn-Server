package com.itechart.crushon.controller

import com.itechart.crushon.dto.authentication.AuthenticationInputDTO
import com.itechart.crushon.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController (
    val authenticationService: AuthenticationService
) {

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody data: AuthenticationInputDTO) =
        authenticationService.authenticate(data.username, data.password)
}
