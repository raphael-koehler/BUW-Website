package de.mbo

import de.mbo.data.Book
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {
    val books = mutableSetOf<Book>()

    embeddedServer(factory = Netty, port = 8080) {
        install(plugin = CORS) {
            anyHost()
            allowMethod(method = HttpMethod.Get)
            allowMethod(method = HttpMethod.Post)
            allowHeader(header = HttpHeaders.ContentType)
            allowHeader(header = HttpHeaders.Accept)
        }

        routing {
            route(path = "/time") {
                get {
                    val currentDateTime = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", Locale.GERMANY)
                    val formattedDateTime = currentDateTime.format(formatter)

                    call.respondText(text = formattedDateTime)
                }
            }

            route(path = "/book") {
                route(path = "/add") {

                }

                route(path = "/remove") {

                }
            }
        }
    }.start(wait = true)
}
