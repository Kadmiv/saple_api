package com.kadmiv.co_share_api.repo.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService : UserDetailsService {
    @Autowired
    private val userRepo: UserRepo? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userLogin: String): UserDetails {
        val user = userRepo!!.findByUserLogin(userLogin)!!
        return user
    }

//    fun getAll(): MutableList<com.kadmiv.co_share_api.models.dto.User?> {
//        return userRepo!!.findAll()
//    }
}