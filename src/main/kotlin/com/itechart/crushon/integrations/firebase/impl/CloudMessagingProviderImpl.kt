package com.itechart.crushon.integrations.firebase.impl

import com.itechart.crushon.integrations.firebase.CloudMessagingProvider
import com.itechart.crushon.model.PushToken
import com.itechart.crushon.model.User
import com.itechart.crushon.repository.PushTokenRepository
import org.springframework.stereotype.Component

@Component
class CloudMessagingProviderImpl(
    private val pushTokenRepository: PushTokenRepository
): CloudMessagingProvider {

    override fun sendNotification(user: User, data: Any) {
        val pushTokens = pushTokenRepository.findByOwner(user)

        println(pushTokens)
        // Send notification
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
