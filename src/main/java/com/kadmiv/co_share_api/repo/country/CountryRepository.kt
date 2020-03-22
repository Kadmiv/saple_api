package com.kadmiv.co_share_api.repo.country

import com.kadmiv.co_share_api.models.dto.Card
import com.kadmiv.co_share_api.models.dto.Company
import com.kadmiv.co_share_api.models.dto.Country
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional
interface CountryRepository : JpaRepository<Country, UUID?> {
}