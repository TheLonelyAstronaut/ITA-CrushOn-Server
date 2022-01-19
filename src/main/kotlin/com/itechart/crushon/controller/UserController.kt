package com.itechart.crushon.controller

import com.itechart.crushon.dto.user.UpdatePhotoDTO
import com.itechart.crushon.dto.user.UpdateUserDTO
import com.itechart.crushon.model.Match
import com.itechart.crushon.model.User
import com.itechart.crushon.service.UserService
import kotlinx.coroutines.flow.Flow
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun getUser(@AuthenticationPrincipal user: User) = user

    @PostMapping("/set_photo")
    fun setPhoto(@AuthenticationPrincipal user: User, @RequestBody data: UpdatePhotoDTO): Long =
        userService.setPhoto(user, data.photoId)

    @PostMapping("/set_firebase_token")
    fun setFirebaseToken(@AuthenticationPrincipal user: User) {
        // add token to firebase repository
    }

    @PostMapping("/update_settings")
    fun updateSettings(@AuthenticationPrincipal user: User, @RequestBody data: UpdateUserDTO) =
        userService.updateUser(user, data)

    @GetMapping("/matches")
    fun getMatches(@AuthenticationPrincipal user: User): Flow<Match> =
        userService.getMatches(user)
}
