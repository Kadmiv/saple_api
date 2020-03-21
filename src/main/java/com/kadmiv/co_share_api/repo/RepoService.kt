package com.kadmiv.co_share_api.repo


import com.kadmiv.co_share_api.models.dto.Card
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service;

@Service
open class RepoService {

    @Autowired
    internal var dataRepository: Repository? = null

    fun insertItem(item: Card) {
//        item.generateUUID()
        dataRepository?.save(item)
    }

    fun insertItems(items: List<Card>) {
//        for (item in items) {
//            item.generateUUID()
//        }
        dataRepository?.saveAll(items)
    }

    fun deleteItem(item: Card) {
        dataRepository?.delete(item)
    }

    fun updateItem(item: Card) {}

    fun getAllItems(): MutableIterable<Card>? {
        return dataRepository?.findAll()
    }

    fun getMostPopularItems(): MutableIterable<Card>? {
        return dataRepository?.getMostPopularItems()
    }
}