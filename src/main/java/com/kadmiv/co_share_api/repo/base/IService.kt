package com.kadmiv.co_share_api.repo.base

import com.kadmiv.co_share_api.models.dto.Card

interface IService<I> {


    fun insertItem(item: I)

    fun insertItems(items: List<I>)

    fun deleteItem(item: I)

    fun updateItem(item: I)

    fun getAllItems(): MutableIterable<I>?

}