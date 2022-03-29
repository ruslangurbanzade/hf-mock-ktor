package com.jetbrains.handson.httpapi.models

import kotlinx.serialization.Serializable

val deliveryStorage = mutableListOf(
    Delivery(12, "06-23-2022", "Aleksandar", 2000),
    Delivery(14, "06-01-2022", "Mehmet", 2500),
    Delivery(16, "06-03-2022", "Suyash", 1000),
    Delivery(17, "06-10-2022", "Roman", 3000),
    Delivery(15, "05-23-2022", "Victor", 2700),
    Delivery(19, "06-15-2022", "Andres", 3300),
    Delivery(20, "06-20-2022", "Faris", 5500),
    Delivery(22, "08-21-2022", "David", 9000),
    Delivery(23, "06-23-2022", "Camilo", 1200),
    Delivery(24, "06-23-2022", "Abdel Rahman", 2300),
    Delivery(25, "06-23-2022", "Ashfaq", 5000),
    Delivery(35, "06-13-2022", "Kate", 5500),
    Delivery(36, "06-23-2022", "Youcef", 10000),
    Delivery(37, "09-23-2022", "Yury", 780),
    Delivery(47, "03-23-2022", "Ruslan", 4500),
    Delivery(57, "06-23-2022", "Oluwafemi", 2760),
)


@Serializable
data class Delivery(val id: Int, val date: String, val customerName: String, val weight: Int)

