package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import com.itechart.crushon.utils.Gender
import javax.persistence.*

@Entity
@Table(name = "user_table")
class User(
    var username: String,
    var name: String,
    @ManyToOne
    var photo: File,
    var bio: String,
    var dateOfBirth: Long,
    var gender: Gender,
    @ManyToMany(fetch = FetchType.EAGER)
    var passions: MutableList<Passion>? = mutableListOf(),
    @ManyToOne
    var city: City
): BaseEntity<Long>()
