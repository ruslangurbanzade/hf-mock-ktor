package com.jetbrains.handson.httpapi.models

import kotlinx.serialization.Serializable

@Serializable
data class Subscription(
    val subsId: String,
    val body: String,
    val description: String,
    val fileNames: String
    )