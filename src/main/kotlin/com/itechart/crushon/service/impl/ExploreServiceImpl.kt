package com.itechart.crushon.service.impl

import com.itechart.crushon.model.City
import com.itechart.crushon.model.Passion
import com.itechart.crushon.model.User
import com.itechart.crushon.repository.CityRepository
import com.itechart.crushon.repository.PassionRepository
import com.itechart.crushon.service.ExploreService
import com.itechart.crushon.utils.Reactions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Service

@Service
class ExploreServiceImpl(
    private val cityRepository: CityRepository,
    private val passionRepository: PassionRepository
): ExploreService {
    override fun getCities(): Flow<City> = cityRepository.findAll().asFlow()

    override fun getPassions(): Flow<Passion> = passionRepository.findAll().asFlow()

    override fun exploreNewPeople(user: User, pageSize: Long, pageNumber: Long): Flow<User>  = flow {
        emit(user)
        emit(user)
        emit(user)
    }

    override fun addReaction(user: User, reactTo: Long, reaction: Reactions): Boolean {
       return (Math.random() * 10).toInt() % 2 == 0
    }
}
