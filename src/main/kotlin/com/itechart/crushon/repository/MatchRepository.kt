package com.itechart.crushon.repository

import com.itechart.crushon.model.Match
import com.itechart.crushon.model.User
import org.springframework.data.repository.CrudRepository

interface MatchRepository: CrudRepository<Match, Long> {
    fun getMatchesByFirst(user: User): Iterable<Match>
    fun getMatchesBySecond(user: User): Iterable<Match>
}
