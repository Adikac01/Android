package pl.edu.uj.android

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import pl.edu.uj.android.controllers.categories
import pl.edu.uj.android.controllers.products
import pl.edu.uj.android.models.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }

    }
    Database.connect("jdbc:sqlite:./data.db", "org.sqlite.JDBC")
    transaction {
        SchemaUtils.create(Products, Categories)
    }

    routing {
        route("/") {
            route("/category") {
                install(CORS){
                    host("0.0.0.0:5000")
                    host("0.0.0.0:3000")
                }
                categories()
            }

            route("/product"){
                install(CORS){
                    host("0.0.0.0:5000")
                    host("0.0.0.0:3000")
                }
                products()
            }
        }

        get("/") {
            call.respond(mapOf("content" to "Hello World!"))
        }
    }
}

