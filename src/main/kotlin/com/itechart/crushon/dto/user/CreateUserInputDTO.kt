package com.itechart.crushon.dto.user

data class CreateUserInputDTO(
    val username: String,
    val name: String,
    val photo: Long,
    val bio: String,
    val age: Long,
    val city: Long
)
