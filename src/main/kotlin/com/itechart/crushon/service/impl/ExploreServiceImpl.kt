package com.itechart.crushon.service.impl

import com.itechart.crushon.model.City
import com.itechart.crushon.model.Passion
import com.itechart.crushon.repository.CityRepository
import com.itechart.crushon.repository.PassionRepository
import com.itechart.crushon.service.ExploreService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.stereotype.Service

@Service
class ExploreServiceImpl(
    private val cityRepository: CityRepository,
    private val passionRepository: PassionRepository
): ExploreService {
    override fun getCities(): Flow<City> = cityRepository.findAll().asFlow()

    override fun getPassions(): Flow<Passion> = passionRepository.findAll().asFlow()
}
