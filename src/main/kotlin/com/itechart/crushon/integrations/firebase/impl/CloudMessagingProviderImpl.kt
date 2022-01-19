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

        val message = MulticastMessage
            .builder()
            .setNotification(
                Notification
                    .builder()
                    .setBody(body)
                    .setTitle(title)
                    .setImage("https://ita-crushon-bucket.s3.eu-central-1.amazonaws.com/placeholder.png")
                    .build()
            )
            .putData("data", ObjectMapper().writeValueAsString(data))
            .addAllTokens(pushTokens.map { it.token })
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
