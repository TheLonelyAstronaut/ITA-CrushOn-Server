package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class PushToken(
    @ManyToOne
    val owner: User,
    val token: String
): BaseEntity<Long>()
