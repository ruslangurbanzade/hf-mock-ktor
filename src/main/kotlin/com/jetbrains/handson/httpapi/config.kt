package com.jetbrains.handson.httpapi

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.HttpHeaders.ContentType
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType

private const val TIME_OUT = 60_000


private val ktorHttpClient = HttpClient(Android) {

    install(JsonFeature) {
        serializer = KotlinxSerializer(Json)

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
               // Log.v("Logger Ktor =>", message)
            }

        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
           // Log.d("HTTP status:", "${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}