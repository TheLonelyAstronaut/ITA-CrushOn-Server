package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import com.itechart.crushon.utils.enums.Reactions
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "reactions")
class Reaction(
    @ManyToOne
    val viewer: User,
    @ManyToOne
    val target: User,
    val reaction: Reactions
): BaseEntity<Long>()
