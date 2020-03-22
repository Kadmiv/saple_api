package com.kadmiv.co_share_api.repo.user

import com.kadmiv.co_share_api.models.dto.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UserRepo : JpaRepository<User?, UUID?> {
    fun findByUserLogin(login: String?): User?
}