package com.itechart.crushon.controller

import com.itechart.crushon.model.City
import com.itechart.crushon.model.Passion
import com.itechart.crushon.service.ExploreService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/explore")
class ExploreController(
    private val exploreService: ExploreService
) {
    @GetMapping("/cities")
    fun getCities(): Flow<City> = exploreService.getCities()

    @GetMapping("/passions")
    fun getPassions(): Flow<Passion> = exploreService.getPassions()
}
