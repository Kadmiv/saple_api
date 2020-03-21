package com.kadmiv.co_share_api.repo

import com.kadmiv.co_share_api.models.dto.Card
import com.kadmiv.co_share_api.models.dto.Category
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface CategoryRepository : JpaRepository<Category, Long?> {
}