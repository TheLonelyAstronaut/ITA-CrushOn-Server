package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "user_table")
class User(
    var username: String,
    var name: String,
    @OneToOne
    var photo: File,
    var bio: String,
    var age: Long,
    @ManyToMany(fetch = FetchType.EAGER)
    var passions: MutableList<Passion>? = mutableListOf(),
    @ManyToOne
    var city: City
): BaseEntity<Long>()
