package com.jetbrains.handson.httpapi.models

import kotlinx.serialization.Serializable

val subscriptions = listOf(
    Subscription("sub1", "", "first subscription", "Subscription01"),
    Subscription("sub2", "", "second subscription", "Subscription02"),
    Subscription("sub3", "", "third subscription", "Subscription03"),
    Subscription("sub4", "", "fourth subscription", "Subscription04"),
    Subscription("sub5", "", "fifth subscription", "Subscription05"),
    Subscription("sub6", "", "sixth subscription", "Subscription06"),
    Subscription("sub7", "", "seventh subscription", "Subscription07"),
    Subscription("sub8", "", "eight subscription", "Subscription08"),
    Subscription("sub9", "", "ninth subscription", "Subscription09"),
)

@Serializable
data class Subscription(val subsId: String, val body: String, val description: String, val fileNames: String)