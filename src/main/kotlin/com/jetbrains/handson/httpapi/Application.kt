package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.routes.registerCustomerRoutes
import com.jetbrains.handson.httpapi.routes.registerDeliveryRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.network.tls.certificates.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory
import java.io.File


//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


//fun main() {
//    val keyStoreFile = File("build/keystore.jks")
//    val keystore = generateCertificate(
//        file = keyStoreFile,
//        keyAlias = "hfAlias",
//        keyPassword = "foobar",
//        jksPassword = "foobar"
//    )
//}

val keyStoreFile = File("build/keystore.jks")
val keystore = generateCertificate(
    file = keyStoreFile,
    keyAlias = "hfAlias",
    keyPassword = "foobar",
    jksPassword = "foobar"
)

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    registerCustomerRoutes()
    registerDeliveryRoute()
}


val environment = applicationEngineEnvironment {
    log = LoggerFactory.getLogger("ktor.application")
    connector {
        port = 8080
    }
    sslConnector(
        keyStore = keystore,
        keyAlias = "hfAlias",
        keyStorePassword = { "foobar".toCharArray() },
        privateKeyPassword = { "foobar".toCharArray() }) {
        port = 8443
        keyStorePath = keyStoreFile
    }
    module(Application::module)
}

fun main() {
    embeddedServer(Netty, environment).start(wait = true)
}

