package com.itechart.crushon.repository

import com.itechart.crushon.model.City
import org.springframework.data.repository.CrudRepository

interface CityRepository: CrudRepository<City, Long> {
    fun findCityById(id: Long): City
}
