package pl.edu.uj.android.services

import org.jetbrains.exposed.sql.transactions.transaction
import pl.edu.uj.android.models.Category
import pl.edu.uj.android.models.CategoryData

class CategoryGet {
    fun get(id: Int): CategoryData? = transaction {
        CategoryData.findById(id)
    }
    fun getAll(): Iterable<Category> = transaction {
        CategoryData.all().map(CategoryData::cat)
    }
    fun create(product: Category): CategoryData = transaction {
        CategoryData.new {
            this.title = product.title
            this.description = product.description
            this.code = product.code
            this.subcategory = product.subcategory
        }
    }
}