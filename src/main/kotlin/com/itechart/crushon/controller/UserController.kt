package com.itechart.crushon.controller

import com.itechart.crushon.model.User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController {

    @GetMapping
    fun getUser(@AuthenticationPrincipal user: User) = user

    @PostMapping("/update_settings")
    fun updateSettings(@AuthenticationPrincipal user: User) {

    }
}
