package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "matches")
class Match(
    @ManyToOne
    val first: User,
    @ManyToOne
    val second: User
): BaseEntity<Long>()
