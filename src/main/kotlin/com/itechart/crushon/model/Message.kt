package com.itechart.crushon.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.itechart.crushon.model.core.BaseEntity
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "messages")
class Message(
    @ManyToOne
    val sender: User,
    val text: String,
    val timestamp: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    val chat: Chat
): BaseEntity<Long>()
