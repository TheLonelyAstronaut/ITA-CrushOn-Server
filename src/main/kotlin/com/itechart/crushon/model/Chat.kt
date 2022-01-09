package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Chat(
    @ManyToOne
    val firstUser: User,
    @ManyToOne
    val secondUser: User,
    @OneToMany
    val messages: MutableList<Message> = mutableListOf()
): BaseEntity<Long>()
