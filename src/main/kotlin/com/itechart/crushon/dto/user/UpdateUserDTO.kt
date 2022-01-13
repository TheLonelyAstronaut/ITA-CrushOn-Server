package com.itechart.crushon.dto.user

data class UpdateUserDTO(
    val photo: Long?,
    val city: Long?,
    val bio: String?,
    val passions: List<Long>?,
    val dateOfBirth: Long?
)
