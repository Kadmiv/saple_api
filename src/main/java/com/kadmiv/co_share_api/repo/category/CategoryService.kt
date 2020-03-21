package com.kadmiv.co_share_api.repo.category


import com.kadmiv.co_share_api.models.dto.Card
import com.kadmiv.co_share_api.models.dto.Category
import com.kadmiv.co_share_api.repo.base.IService
import com.kadmiv.co_share_api.repo.card.CardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service;

@Service
open class CategoryService : IService<Category> {

    @Autowired
    internal var repo: CategoryRepository? = null

    override fun insertItem(item: Category) {
        repo?.save(item)
    }

    override fun insertItems(items: List<Category>) {
        repo?.saveAll(items)
    }

    override fun deleteItem(item: Category) {
        repo?.delete(item)
    }

    override fun updateItem(item: Category) {}


    override fun getAllItems(): MutableIterable<Category>? {
        return repo?.findAll()
    }
}