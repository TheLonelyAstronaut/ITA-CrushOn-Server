package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "messages")
class Message(
    @ManyToOne
    val sender: User,
    val text: String
): BaseEntity<Long>()
