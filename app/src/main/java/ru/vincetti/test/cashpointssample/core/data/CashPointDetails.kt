package ru.vincetti.test.cashpointssample.core.data

data class CashPointDetails(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val image: String,
    val address: String,
    val workHours: String?,
    val phones: String?,
    val description: String?
)
