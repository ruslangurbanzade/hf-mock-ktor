package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.Delivery
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

fun Route.deliveryRouting(col: CoroutineCollection<Delivery>) {
    route("/delivery") {
        get {
            val deliveryStorage = col.find().toList()
            if (deliveryStorage.isNotEmpty()) {
                call.respond(col.find().toList())
            } else {
                call.respondText("No customers found", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}") {
            val deliveryStorage = col.find().toList()
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
            val deliveryStorage = col.find().toList()
            val requestBody = call.receive<Delivery>()
            if (deliveryStorage.isEmpty() || deliveryStorage.toList().none { it.id == requestBody.id }) {
                val delivery = requestBody.copy(id = deliveryStorage.size.plus(1))
                col.insertOne(delivery)
                call.respondText("Delivery stored correctly", status = HttpStatusCode.Created)
            } else {
                call.respondText("Delivery already added", status = HttpStatusCode.Conflict)
            }
        }

        delete("{id}") {
            val deliveryStorage = col.find().toList()
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            deliveryStorage.find { it.id == id.toInt() } ?: return@delete call.respondText(
                "No customer with id $id",
                status = HttpStatusCode.NotFound
            )
            col.deleteOne(Delivery::id eq id.toInt())
            call.respondText("Delivery deleted successfully")
        }
    }
}


fun Application.registerDeliveryRoute(col: CoroutineCollection<Delivery>) {
    routing {
        deliveryRouting(col)
    }
}