package com.itechart.crushon.config

import org.hibernate.SessionFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory


@EnableAutoConfiguration
@EnableTransactionManagement
class HibernateConfig {

    @Bean
    fun sessionFactory(factory: EntityManagerFactory): SessionFactory {
        if (factory.unwrap(SessionFactory::class.java) == null) {
            throw NullPointerException("factory is not a hibernate factory")
        }

        return factory.unwrap(SessionFactory::class.java)
    }
}
