package pl.edu.uj.android.controllers


import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.response.*
import org.jetbrains.exposed.sql.transactions.transaction
import pl.edu.uj.android.models.*

fun get(id: Int): ProductData? = transaction {
    ProductData.findById(id)
}

fun getAll(): Iterable<Product> = transaction {
    ProductData.all().map(ProductData::prod)
}

fun create(product: Product): ProductData = transaction {
    ProductData.new {
        this.title = product.title
        this.description = product.description
        this.price = product.price
        this.categoryId = product.categoryId
    }
}

fun Route.products(){
    get("/{id}") {
        val productId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        val productData = get(productId) ?: throw NotFoundException()
        call.respond(productData.prod())
    }
    get("") {
        val products = getAll()
        call.respond(products)
    }
    post("") {
        val productInRequest = call.receive<Product>()
        val productData = create(productInRequest)
        call.respond(productData.prod())
    }
}
