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
        TODO("Not yet implemented")
    }

    override fun registerPushToken(user: User, token: String) {
        val row = PushToken(user, token)

        pushTokenRepository.save(row)
    }
}
