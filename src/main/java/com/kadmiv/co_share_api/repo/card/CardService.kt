package com.kadmiv.co_share_api.repo.card


import com.kadmiv.co_share_api.models.dto.Card
import com.kadmiv.co_share_api.repo.base.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service;

@Service
open class CardService : IService<Card> {

    @Autowired
    internal var repo: CardRepository? = null

    override fun insertItem(item: Card) {
//        item.generateUUID()
        repo?.save(item)
    }

    override fun insertItems(items: List<Card>) {
//        for (item in items) {
//            item.generateUUID()
//        }
        repo?.saveAll(items)
    }

    override fun deleteItem(item: Card) {
        repo?.delete(item)
    }

    override fun updateItem(item: Card) {}


    override fun getAllItems(): MutableIterable<Card>? {
        return repo?.findAll()
    }

    fun getMostPopularItems(): MutableIterable<Card>? {
        return repo?.getMostPopularItems()
    }
}
