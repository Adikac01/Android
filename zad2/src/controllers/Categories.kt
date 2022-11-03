package pl.edu.uj.android.controllers


import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.response.*
import pl.edu.uj.android.models.Category
import pl.edu.uj.android.services.CategoryGet

fun Route.categories(){
    val category = CategoryGet()
    get("/{id}") {
        val categoryId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        val categoryEntity = category.get(categoryId) ?: throw NotFoundException()
        call.respond(categoryEntity.cat())
    }
    get("") {
        val products = category.getAll()
        call.respond(products)
    }
    post("") {
        val categoryInRequest = call.receive<Category>()
        val categoryEntity = category.create(categoryInRequest)
        call.respond(categoryEntity.cat())
    }
}
