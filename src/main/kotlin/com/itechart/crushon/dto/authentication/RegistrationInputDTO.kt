package com.itechart.crushon.dto.authentication

data class RegistrationInputDTO(
    val username: String,
    val password: String,
    val name: String,
    val photo: Long,
    val bio: String,
    val age: Long,
    val city: Long
)
