package com.itechart.crushon.dto.explore

import com.itechart.crushon.model.User

data class AddReactionOutputDTO (
    val isMatch: Boolean,
    val target: User
)
