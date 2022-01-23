package com.itechart.crushon.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.itechart.crushon.dto.chat.SendMessageOutputDTO
import com.itechart.crushon.integrations.firebase.FirebaseProvider
import com.itechart.crushon.model.Chat
import com.itechart.crushon.model.Message
import com.itechart.crushon.model.User
import com.itechart.crushon.repository.ChatRepository
import com.itechart.crushon.repository.MessageRepository
import com.itechart.crushon.repository.UserRepository
import com.itechart.crushon.service.ChatService
import com.itechart.crushon.utils.socket.SocketConnectionPool
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatServiceImpl(
    private val chatRepository: ChatRepository,
    private val messageRepository: MessageRepository,
    private val pool: SocketConnectionPool,
    private val firebaseProvider: FirebaseProvider
): ChatService {
    override fun sendMessage(user: User, sendTo: Long, message: String): SendMessageOutputDTO {
        val chat = chatRepository.findById(sendTo).get()

        val dbMessage = Message(user, message, Date().time, chat)
        messageRepository.save(dbMessage)

        val receiver = if(chat.firstUser.id === user.id) chat.secondUser else chat.firstUser

        pool.send(receiver.id!!, ObjectMapper().writeValueAsString(object {
            val id = dbMessage.id
            val text = dbMessage.text
            val timestamp = dbMessage.timestamp
            val sender = dbMessage.sender
            val chatId = sendTo
        }));

        firebaseProvider.cloudMessaging.sendNotification(receiver, user.name, message, dbMessage)

        return SendMessageOutputDTO(
            dbMessage.timestamp,
            dbMessage.id!!
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getChats(user: User): Flow<Chat> {
        return merge(
            chatRepository.findByFirstUser(user).asFlow(),
            chatRepository.findBySecondUser(user).asFlow()
        ).map {
            val chat = Chat(it.firstUser, it.secondUser)
            chat.id = it.id

            val lastMessage = messageRepository.findFirstByChatOrderByIdDesc(it)

            if(lastMessage != null) {
                chat.messages.add(lastMessage)
            } else {
                val message = Message(user, "No messages yet...", Date().time, it)
                val firstUserId = it.firstUser.id!!.toString()
                val secondUserId = it.secondUser.id!!.toString()

                message.id = "-${firstUserId}${secondUserId}".toLong()

                chat.messages.add(message)
            }

            chat
        }
    }

    override fun getMessages(user: User, chatId: Long): Flow<Message> {
        val chat = chatRepository.findById(chatId).get()

        return chat.messages.asFlow()
    }
}
