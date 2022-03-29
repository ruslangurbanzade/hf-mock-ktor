package com.jetbrains.handson.httpapi.models

import kotlinx.serialization.Serializable

val subscriptions = mutableListOf(
    Subscription("1", "", "first subscription", "Subscription01"),
    Subscription("2", "", "second subscription", "Subscription02"),
    Subscription("3", "", "third subscription", "Subscription03"),
    Subscription("4", "", "fourth subscription", "Subscription04"),
    Subscription("5", "", "fifth subscription", "Subscription05"),
    Subscription("6", "", "sixth subscription", "Subscription06"),
    Subscription("7", "", "seventh subscription", "Subscription07"),
    Subscription("8", "", "eight subscription", "Subscription08"),
    Subscription("9", "", "ninth subscription", "Subscription09")
)

@Serializable
data class Subscription(val subsId: String, val body: String, val description: String, val fileNames: String)