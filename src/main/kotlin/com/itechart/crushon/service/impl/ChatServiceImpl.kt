package com.itechart.crushon.service.impl

import com.itechart.crushon.model.Chat
import com.itechart.crushon.model.Message
import com.itechart.crushon.model.User
import com.itechart.crushon.repository.ChatRepository
import com.itechart.crushon.repository.MessageRepository
import com.itechart.crushon.service.ChatService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatServiceImpl(
    private val chatRepository: ChatRepository,
    private val messageRepository: MessageRepository
): ChatService {
    override fun sendMessage(user: User, sendTo: Long, message: String): Long {
        return Date().time
    }

    override fun getChats(user: User): Flow<Chat> = flow {
        emit(Chat(
            firstUser = user,
            secondUser = user
        ))
    }

    override fun getMessages(user: User, chatId: Long): Flow<Message> = flow {
        emit(Message(
            sender = user,
            text = "Message!"
        ))
    }
}
