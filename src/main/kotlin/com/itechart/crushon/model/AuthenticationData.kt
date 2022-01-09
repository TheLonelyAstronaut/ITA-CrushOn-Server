package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
    name = "authentication_data",
    uniqueConstraints = [UniqueConstraint(columnNames = ["username"])]
)
class AuthenticationData(
    val username: String,
    val passwordHash: String
): BaseEntity<Long>()
