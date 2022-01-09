package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.Entity

@Entity
class City (
    val name: String
): BaseEntity<Long>()
