package pl.edu.uj.android.models
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Categories : IntIdTable() {
    val title = varchar("title", 50)
    val description = varchar("description", 1024)
    val code = integer("code")
    val subcategory = varchar("subcategory", 50)
}
class CategoryData(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CategoryData>(Categories)
    var title by Categories.title
    var description by Categories.description
    var code by Categories.code
    var subcategory by Categories.subcategory
    fun cat() = Category(id.value, title, description, code, subcategory)
}
data class Category(val id: Int, val title: String, val description: String, val code: Int, val subcategory: String)