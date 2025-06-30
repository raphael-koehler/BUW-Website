package de.mbo

import de.mbo.data.Recipe
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    val recipes = mutableSetOf<Recipe>()
    var nextId = 1

    recipes.add(Recipe(id = 0, name = "Nudeln"))

    embeddedServer(factory = Netty, port = 8080) {
        install(plugin = CORS) {
            anyHost()
            allowMethod(method = HttpMethod.Get)
            allowMethod(method = HttpMethod.Post)
            allowHeader(header = HttpHeaders.ContentType)
            allowHeader(header = HttpHeaders.Accept)
        }

        install(plugin = ContentNegotiation) {
            json()
        }

        routing {
            route(path = "/recipe") {
                get {
                    call.respond(recipes)
                }

                post {
                    val recipe = call.receive<Recipe>()
                    recipe.copy(id = nextId++).also { recipes.add(it) }
                    call.respond(status = HttpStatusCode.Created, message = "Book added with ID: ${nextId - 1}")
                }

                get("/{id}") {
                    val id = call.parameters["id"]?.toIntOrNull()
                    val recipe = recipes.find { it.id == id }
                    if (recipe != null) {
                        call.respond(recipe)
                    } else {
                        call.respond(status = HttpStatusCode.NotFound, message = "Book not found")
                    }
                }

                delete("/{id}") {
                    val id = call.parameters["id"]?.toIntOrNull()
                    val recipe = recipes.find { it.id == id }
                    if (recipe != null) {
                        recipes.remove(recipe)
                        call.respond(HttpStatusCode.NoContent)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Book not found")
                    }
                }
            }
        }
    }.start(wait = true)
}
