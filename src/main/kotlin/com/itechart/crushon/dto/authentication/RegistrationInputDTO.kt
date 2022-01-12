package com.itechart.crushon.dto.authentication

data class RegistrationInputDTO(
    val username: String,
    val password: String,
    val name: String,
    val bio: String,
    val dateOfBirth: Long,
    val city: Long,
    val gender: String
)
