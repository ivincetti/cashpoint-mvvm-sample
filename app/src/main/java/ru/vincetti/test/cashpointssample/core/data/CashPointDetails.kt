package ru.vincetti.test.cashpointssample.core.data

import ru.vincetti.test.cashpointssample.core.network.models.partners.DailyLimit

data class CashPointDetails(
    val id: String,
    val name: String,
    val image: String,
    val address: String,
    val workHours: String?,
    val phones: String?,
    val dailyLimits: List<DailyLimit>?,
    val description: String?
)
