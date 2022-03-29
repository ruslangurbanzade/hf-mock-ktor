package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.models.Delivery
import com.jetbrains.handson.httpapi.models.Subscription
import com.jetbrains.handson.httpapi.routes.registerCustomerRoutes
import com.jetbrains.handson.httpapi.routes.registerDeliveryRoute
import com.jetbrains.handson.httpapi.routes.registerSubscriptionRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.slf4j.LoggerFactory

lateinit var collection: CoroutineCollection<Delivery>
lateinit var subscriptions: CoroutineCollection<Subscription>

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    registerCustomerRoutes()
    registerDeliveryRoute(collection)
    registerSubscriptionRoutes(subscriptions)
}

val environment = applicationEngineEnvironment {
    log = LoggerFactory.getLogger("ktor.application")
    connector {
        host = "127.0.0.1"
        port = 8080
    }
    module(Application::module)
}

fun main() {
    val client = KMongo.createClient().coroutine
    val database = client.getDatabase("delivery")
    collection = database.getCollection()
    subscriptions = database.getCollection()
    embeddedServer(Netty, environment).start(wait = true)
}

