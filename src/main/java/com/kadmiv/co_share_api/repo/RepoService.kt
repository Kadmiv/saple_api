package com.kadmiv.co_share_api.repo


import com.kadmiv.co_share_api.models.dto.Card
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service;

@Service
open class RepoService {

    @Autowired
    internal var cardRepo: CardRepository? = null

    @Autowired
    internal var categoryRepo: CategoryRepository? = null

    @Autowired
    internal var companyRepo: CompanyRepository? = null

    fun insertItem(item: Card) {
//        item.generateUUID()
        cardRepo?.save(item)
    }

    fun insertItems(items: List<Card>) {
//        for (item in items) {
//            item.generateUUID()
//        }
        cardRepo?.saveAll(items)
    }

    fun deleteItem(item: Card) {
        cardRepo?.delete(item)
    }

    fun updateItem(item: Card) {}

    fun getAllItems(): MutableIterable<Card>? {
        return cardRepo?.findAll()
    }

    fun getMostPopularItems(): MutableIterable<Card>? {
        return cardRepo?.getMostPopularItems()
    }
}