package com.itechart.crushon.repository

import com.itechart.crushon.model.File
import org.springframework.data.repository.CrudRepository

interface FileRepository: CrudRepository<File, Long> {
    fun findFileById(id: Long): File
}
