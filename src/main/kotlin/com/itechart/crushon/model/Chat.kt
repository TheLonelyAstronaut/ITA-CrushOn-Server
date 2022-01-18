package com.itechart.crushon.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.itechart.crushon.model.core.BaseEntity
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Chat(
    @ManyToOne
    val firstUser: User,
    @ManyToOne
    val secondUser: User,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    @Fetch(FetchMode.JOIN)
    @JsonManagedReference
    val messages: MutableSet<Message> = mutableSetOf()
): BaseEntity<Long>()
