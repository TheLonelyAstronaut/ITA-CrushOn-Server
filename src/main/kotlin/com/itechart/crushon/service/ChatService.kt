package com.itechart.crushon.service

import com.itechart.crushon.model.Chat
import com.itechart.crushon.model.Message
import com.itechart.crushon.model.User
import kotlinx.coroutines.flow.Flow

interface ChatService {
    fun sendMessage(user: User, sendTo: Long, message: String): Long
    fun getChats(user: User): Flow<Chat>
    fun getMessages(user: User, chatId: Long): Flow<Message>
}
