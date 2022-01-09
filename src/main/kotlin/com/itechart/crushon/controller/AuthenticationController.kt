package com.itechart.crushon.controller

import com.itechart.crushon.dto.authentication.AuthenticationInputDTO
import com.itechart.crushon.dto.authentication.AuthenticationOutputDTO
import com.itechart.crushon.dto.authentication.RefreshTokensInputDTO
import com.itechart.crushon.dto.authentication.RegistrationInputDTO
import com.itechart.crushon.dto.user.CreateUserInputDTO
import com.itechart.crushon.service.AuthenticationService
import com.itechart.crushon.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController (
    private val authenticationService: AuthenticationService,
    private val userService: UserService
) {

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody data: AuthenticationInputDTO) =
        authenticationService.authenticate(data.username, data.password)

    @PostMapping("/refresh_tokens")
    fun refreshTokens(@RequestBody data: RefreshTokensInputDTO) =
        authenticationService.refreshTokens(data.refreshToken)

    @PostMapping("/register")
    fun register(@RequestBody data: RegistrationInputDTO): AuthenticationOutputDTO {
        authenticationService.createAuthenticationRecord(data.username, data.password)

        userService.createUser(CreateUserInputDTO(
            username = data.username,
            name = data.name,
            photo = data.photo,
            bio = data.bio,
            age = data.age,
            city = data.city
        ))

        return authenticationService.authenticate(data.username, data.password)
    }
}
