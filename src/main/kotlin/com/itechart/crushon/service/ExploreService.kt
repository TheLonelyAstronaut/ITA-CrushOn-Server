package com.itechart.crushon.service

import com.itechart.crushon.model.City
import com.itechart.crushon.model.Passion
import kotlinx.coroutines.flow.Flow

interface ExploreService {
    fun getCities(): Flow<City>
    fun getPassions(): Flow<Passion>
}
