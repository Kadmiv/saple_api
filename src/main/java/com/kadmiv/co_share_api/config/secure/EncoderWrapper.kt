package com.kadmiv.co_share_api.config.secure

import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class EncoderWrapper {

    //    private val encoder = NoOpPasswordEncoder.getInstance()
    //        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
    val encoder = BCryptPasswordEncoder()


    fun getEncoder(): PasswordEncoder {
        return encoder
    }
}