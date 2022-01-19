package com.itechart.crushon.integrations.firebase

import com.itechart.crushon.model.User

interface CloudMessagingProvider {
    fun sendNotification(user: User, data: Any)
    fun registerPushToken(user: User, token: String)
    fun removePushToken(user: User, token: String)
}
