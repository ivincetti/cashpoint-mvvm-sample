package ru.vincetti.test.cashpointssample.core.network.models.partners

import com.google.gson.annotations.SerializedName

data class DailyLimit(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("currency")
    val currency: Currency?
)
