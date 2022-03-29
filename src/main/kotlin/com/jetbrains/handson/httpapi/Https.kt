package com.jetbrains.handson.httpapi

import io.ktor.network.tls.certificates.*
import io.ktor.network.tls.extensions.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.io.File
import java.security.KeyStore

class HttpsServer {
    val pass = "abcd1234"

    val server: NettyApplicationEngine

    init {
        server = createServer()
    }

    private fun createServer(): NettyApplicationEngine {
        val alias = "certificateAlias"

        val keystore = buildKeyStore {
            certificate(alias) {
                hash = HashAlgorithm.SHA256
                sign = SignatureAlgorithm.ECDSA
                keySizeInBits = 256
                password = pass
            }
        }

        val server = embeddedServer(Netty, applicationEngineEnvironment {
            sslConnector(keystore,
                alias,
                { "".toCharArray() },
                { pass.toCharArray() }) {
                port = 8181
                keyStorePath = keyStore.asFile.absoluteFile

                module {
                    // Your usual definitions, such as Content Negotiation, go here
                    routing {
                        // Your routes go here
                    }
                }
            }
        })

        return server
    }

    fun start() {
        server.start()
    }

    private val KeyStore.asFile: File
        get() {
            val keyStoreFile = File("build/temp.jks")
            this.saveToFile(keyStoreFile, pass)
            return keyStoreFile
        }

}

fun main(){
    HttpsServer().start()
}