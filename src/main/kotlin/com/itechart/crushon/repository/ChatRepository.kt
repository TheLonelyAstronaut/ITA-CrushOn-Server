package com.itechart.crushon.repository

import com.itechart.crushon.model.Chat
import org.springframework.data.repository.CrudRepository

interface ChatRepository: CrudRepository<Chat, Long>
