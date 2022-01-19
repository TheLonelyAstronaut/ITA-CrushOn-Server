package com.itechart.crushon.integrations.firebase.impl

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.itechart.crushon.integrations.firebase.CloudMessagingProvider
import com.itechart.crushon.integrations.firebase.FirebaseProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import javax.annotation.PostConstruct

@Component
class FirebaseProviderImpl(
    override val cloudMessaging: CloudMessagingProvider,
    @Value("\${firebase.service.json}")
    private val serviceAccountJSON: String,
): FirebaseProvider {

    @PostConstruct
    private fun initialize() {
        val options = FirebaseOptions
            .builder()
            .setCredentials(GoogleCredentials.fromStream(ByteArrayInputStream(serviceAccountJSON.toByteArray())))
            .build()

        FirebaseApp.initializeApp(options)
    }
}
