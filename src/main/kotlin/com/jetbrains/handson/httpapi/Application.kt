package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.routes.registerCustomerRoutes
import com.jetbrains.handson.httpapi.routes.registerSubscriptionRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.network.tls.certificates.*
import io.ktor.serialization.*
import java.io.File


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation){
        json()
    }
    registerCustomerRoutes()
    registerSubscriptionRoutes()
}

/*
fun main() {
    val keyStoreFile = File("build/keystore.jks")
    val keystore = generateCertificate(
        file = keyStoreFile,
        keyAlias = "sampleAlias",
        keyPassword = "foobar",
        jksPassword = "foobar"
    )
}*/
