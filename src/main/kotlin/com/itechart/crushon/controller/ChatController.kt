package com.itechart.crushon.controller

import com.itechart.crushon.dto.chat.GetSpecificChatDTO
import com.itechart.crushon.dto.chat.SendMessageInputDTO
import com.itechart.crushon.dto.chat.SendMessageOutputDTO
import com.itechart.crushon.model.Chat
import com.itechart.crushon.model.Message
import com.itechart.crushon.model.User
import com.itechart.crushon.service.ChatService
import kotlinx.coroutines.flow.Flow
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/chats")
class ChatController(
    private val chatService: ChatService
) {
    @GetMapping
    fun getUserChats(@AuthenticationPrincipal user: User): Flow<Chat> =
        chatService.getChats(user)

    @GetMapping("/messages")
    fun getSpecificChat(@AuthenticationPrincipal user: User, @RequestBody data: GetSpecificChatDTO): Flow<Message> =
        chatService.getMessages(user, data.chatId)

    @PostMapping("/send")
    fun sendMessage(@AuthenticationPrincipal user: User, @RequestBody data: SendMessageInputDTO): SendMessageOutputDTO =
        SendMessageOutputDTO(
            timestamp = chatService
                .sendMessage(
                    user,
                    data.chatId,
                    data.message
                )
        )
}

