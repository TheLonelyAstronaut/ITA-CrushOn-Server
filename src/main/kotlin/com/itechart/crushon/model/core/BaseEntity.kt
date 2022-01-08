package com.itechart.crushon.model.core

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity<T: Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: T? = null
}
