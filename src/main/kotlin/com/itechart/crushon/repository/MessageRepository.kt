package com.itechart.crushon.repository

import com.itechart.crushon.model.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, Long>
