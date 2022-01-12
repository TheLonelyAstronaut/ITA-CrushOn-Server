package com.itechart.crushon.dto.user

data class CreateUserInputDTO(
    val username: String,
    val name: String,
    val bio: String,
    val dateOfBirth: Long,
    val gender: String,
    val city: Long
)
