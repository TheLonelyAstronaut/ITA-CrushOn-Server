package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "views")
class View(
    @ManyToOne
    val viewer: User,
    @ManyToOne
    val target: User,
): BaseEntity<Long>()
