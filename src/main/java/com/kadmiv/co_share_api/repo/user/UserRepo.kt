package com.kadmiv.co_share_api.repo.user

import com.kadmiv.co_share_api.models.dto.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepo : JpaRepository<User?, Long?> {
    fun findByUserLogin(login: String?): User?
}