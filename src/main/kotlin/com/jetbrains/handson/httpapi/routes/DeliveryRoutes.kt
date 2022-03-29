package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.Delivery
import com.jetbrains.handson.httpapi.models.deliveryStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.deliveryRouting() {
    route("/delivery") {
        get {
            if (deliveryStorage.isNotEmpty()) {
                call.respond(deliveryStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val delivery = deliveryStorage.find { it.id == id.toInt() } ?: return@get call.respondText(
                "No customer with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(delivery)
        }
        post() {
            val delivery = call.receive<Delivery>()
            if (deliveryStorage.isEmpty() || deliveryStorage.none { it.id == delivery.id }) {
                deliveryStorage.add(delivery)
                call.respondText("Delivery stored correctly", status = HttpStatusCode.Created)
            } else {
                call.respondText("Delivery already added", status = HttpStatusCode.Conflict)
            }
        }
    }
}


fun Application.registerDeliveryRoute() {
    routing {
        deliveryRouting()
    }
}