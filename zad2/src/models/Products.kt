package pl.edu.uj.android.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Products : IntIdTable() {
    val title = varchar("title", 50) // Column<String>
    val description = varchar("description", 1024) // Column<String>
    val price = integer("price")
    val categoryId = integer("category_id").references(Categories.id)

}
class ProductData(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProductData>(Products)
    var title by Products.title
    var description by Products.description
    var price by Products.price
    var categoryId by Products.categoryId
    fun prod() = Product(id.value, title, description, price, categoryId)
}
data class Product(val id: Int, val title: String, val description: String, val price: Int, val categoryId: Int)
