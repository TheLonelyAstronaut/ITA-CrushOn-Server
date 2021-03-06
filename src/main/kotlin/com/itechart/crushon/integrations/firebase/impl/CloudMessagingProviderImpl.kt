package com.itechart.crushon.integrations.firebase.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.firebase.messaging.*
import com.itechart.crushon.integrations.firebase.CloudMessagingProvider
import com.itechart.crushon.model.PushToken
import com.itechart.crushon.model.User
import com.itechart.crushon.repository.PushTokenRepository
import org.springframework.stereotype.Component

@Component
class CloudMessagingProviderImpl(
    private val pushTokenRepository: PushTokenRepository
): CloudMessagingProvider {

    override fun sendNotification(user: User, title: String, body: String, data: Any) {
        val pushTokens = pushTokenRepository.findByOwner(user)
        val tokens = pushTokens.map { it.token }

        if(tokens.isEmpty()) return

        val message = MulticastMessage
            .builder()
            .setNotification(
                Notification
                    .builder()
                    .setBody(body)
                    .setTitle(title)
                    .setImage(user.photo.link)
                    .build()
            )
            .putData("data", ObjectMapper().writeValueAsString(data))
            .addAllTokens(tokens)
            .build()

        FirebaseMessaging.getInstance().sendMulticast(message)
    }

    override fun registerPushToken(user: User, token: String) {
        val row = PushToken(user, token)

        pushTokenRepository.save(row)
    }

    override fun removePushToken(user: User, token: String) {
        pushTokenRepository.findByOwnerAndToken(user, token)?.let {
            pushTokenRepository.delete(it)
        }
    }
}
