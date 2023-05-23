package com.jeonghyeon00.kotlin.carrot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@EnableRedisRepositories
class CarrotMarketApplication

fun main(args: Array<String>) {
    runApplication<CarrotMarketApplication>(*args)
}
