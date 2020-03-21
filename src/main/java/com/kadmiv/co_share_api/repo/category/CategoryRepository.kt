package com.kadmiv.co_share_api.repo.category

import com.kadmiv.co_share_api.models.dto.Category
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface CategoryRepository : JpaRepository<Category, Long?> {
}