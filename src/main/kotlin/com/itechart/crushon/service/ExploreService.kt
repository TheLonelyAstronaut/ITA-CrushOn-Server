package com.itechart.crushon.service

import com.itechart.crushon.dto.explore.AddReactionOutputDTO
import com.itechart.crushon.model.City
import com.itechart.crushon.model.Passion
import com.itechart.crushon.model.User
import com.itechart.crushon.utils.enums.Reactions
import kotlinx.coroutines.flow.Flow

interface ExploreService {
    fun getCities(): Flow<City>
    fun getPassions(): Flow<Passion>
    fun exploreNewPeople(user: User): Flow<User>
    fun addReaction(user: User, reactTo: Long, reaction: Reactions): AddReactionOutputDTO
}
