package akshaw.com.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.response.*

fun Application.configureHTTP() {
    install(DefaultHeaders)
}
