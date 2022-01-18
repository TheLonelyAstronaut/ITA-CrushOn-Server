package com.itechart.crushon.repository

import com.itechart.crushon.model.User
import com.itechart.crushon.model.View
import org.springframework.data.repository.CrudRepository

interface ViewRepository: CrudRepository<View, Long> {
    fun findByViewerAndTarget(viewer: User, target: User): View
}
