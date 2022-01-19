package com.itechart.crushon.integrations.firebase.impl

import com.itechart.crushon.integrations.firebase.CloudMessagingProvider
import com.itechart.crushon.integrations.firebase.FirebaseProvider
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class FirebaseProviderImpl(
    override val cloudMessaging: CloudMessagingProvider
): FirebaseProvider {

    @PostConstruct
    private fun initialize() {
        //Do Firebase init here
    }
}
