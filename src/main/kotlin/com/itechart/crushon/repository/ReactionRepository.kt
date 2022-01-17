package com.itechart.crushon.repository

import com.itechart.crushon.model.Reaction
import org.springframework.data.repository.CrudRepository

interface ReactionRepository: CrudRepository<Reaction, Long>
