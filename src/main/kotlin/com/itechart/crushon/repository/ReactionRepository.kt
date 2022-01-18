package com.itechart.crushon.repository

import com.itechart.crushon.model.Reaction
import com.itechart.crushon.model.User
import org.springframework.data.repository.CrudRepository

interface ReactionRepository: CrudRepository<Reaction, Long> {
    fun getReactionByViewer(viewer: User): Reaction?
}
