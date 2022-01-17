package com.itechart.crushon.model

import com.itechart.crushon.model.core.BaseEntity
import javax.persistence.Entity

@Entity
class City (
    val en: String,
    val be: String,
    val ru: String
): BaseEntity<Long>()
