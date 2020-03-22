package com.kadmiv.co_share_api

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.event.EventListener
import org.springframework.core.env.Environment
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication
//@ComponentScan(
//        value = [
//            "com.kadmiv.co_share_api.controllers",
//            "com.kadmiv.co_share_api.config",
//            "com.kadmiv.co_share_api.repo"
//        ]
//)
//@EntityScan("com.kadmiv.co_share_api.models")
open class Application {

    private val LOG = LoggerFactory.getLogger(Application::class.java)

    @Autowired
    var environment: Environment? = null

    @EventListener(ApplicationReadyEvent::class)
    open fun doSomethingAfterStartup() {
        var port = environment?.getProperty("local.server.port")

        LOG.info("http://localhost:$port")
        LOG.info("http://localhost:$port/swagger-ui.html")

//        val cardSample = Card().apply {
//            name = "some card"
//            code = "some card code"
//            bonusPercent = 10.0
//            rating = 1.0
//            followersCount = 123
//        }
//
//        println(Gson().toJson(cardSample))
    }

    @PostConstruct
    open fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        println("Date in UTC: " + Date().toString())
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
