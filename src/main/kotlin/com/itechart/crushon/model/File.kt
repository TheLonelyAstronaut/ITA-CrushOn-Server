package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.Entity

@Entity
class File (
    val link: String
): BaseEntity<Long>()
