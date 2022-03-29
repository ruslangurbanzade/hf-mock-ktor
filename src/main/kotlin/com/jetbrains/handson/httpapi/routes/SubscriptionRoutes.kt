package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.Subscription
import com.jetbrains.handson.httpapi.models.subscriptions
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.subscriptionRoutes() {
    route("/subscriptions") {
        get {
            if (subscriptions.isNotEmpty()) {
                call.respond(subscriptions)
            } else {
                call.respondText("No subscriptions found", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val customer = subscriptions.find { it.subsId == id } ?: return@get call.respondText(
                "No subscription with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(customer)
        }

        post {
            val subscription = call.receive<Subscription>()
            if (subscription.subsId.isEmpty() || subscription.body.isEmpty()) {
                call.respondText("Invalid subscription", status = HttpStatusCode.BadRequest)
            } else if (subscriptions.any { it.subsId == subscription.subsId }) {
                call.respondText("Subscription already added", status = HttpStatusCode.Conflict)
            } else {
                subscriptions.add(subscription)
                call.respondText("Subscription added successfully", status = HttpStatusCode.Created)
            }
        }
    }
}

fun Application.registerSubscriptionRoutes() {
    routing {
        subscriptionRoutes()
    }
}