package ru.vincetti.test.cashpointssample.core.network.models.partners

import com.google.gson.annotations.SerializedName

data class Limit(
    @SerializedName("currency")
    val currency: CurrencyX?,
    @SerializedName("max")
    val max: Int?,
    @SerializedName("min")
    val min: Int?
)
