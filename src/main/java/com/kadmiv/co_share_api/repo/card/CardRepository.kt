package com.kadmiv.co_share_api.repo.card

import com.kadmiv.co_share_api.models.dto.Card
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface CardRepository : JpaRepository<Card, Long?> {

    @Query("SELECT item FROM Card item ORDER BY rating DESC, followersCount DESC")
    fun getMostPopularItems(): MutableIterable<Card>?

//    @Query("SELECT item FROM DataModel item WHERE date >= ?1")
//    fun getAllItemsHigherTime(data: Long): MutableIterable<Customer>?
//
//    @Query("SELECT item FROM DataModel item WHERE date >= ?1 AND companyID = ?2")
//    fun getAllItemsByTimeForCompany(time: Long, companyID: Long): MutableIterable<Customer>?
//
//    @Query("SELECT MAX(date) FROM DataModel WHERE companyID = ?1")
//    fun getMaxDataTime(companyID: Long): Long
}