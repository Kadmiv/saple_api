package com.kadmiv.co_share_api.repo.company


import com.kadmiv.co_share_api.models.dto.Company
import com.kadmiv.co_share_api.repo.base.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class CompanyService : IService<Company> {

    @Autowired
    internal var repo: CompanyRepository? = null

    override fun insertItem(item: Company) {
        repo?.save(item)
    }

    override fun insertItems(items: List<Company>) {
        repo?.saveAll(items)
    }

    override fun deleteItem(item: Company) {
        repo?.delete(item)
    }

    override fun updateItem(item: Company) {}


    override fun getAllItems(): MutableIterable<Company>? {
        return repo?.findAll()
    }
}