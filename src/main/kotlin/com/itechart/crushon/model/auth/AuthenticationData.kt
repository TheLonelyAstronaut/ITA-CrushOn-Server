package com.itechart.crushon.model.auth

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.Entity

@Entity
class AuthenticationData(
    val username: String,
    val passwordHash: String
): BaseEntity<Long>()
