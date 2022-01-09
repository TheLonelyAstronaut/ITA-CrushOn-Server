package com.itechart.crushon.repository

import com.itechart.crushon.model.Passion
import org.springframework.data.repository.CrudRepository

interface PassionRepository: CrudRepository<Passion, Long>
