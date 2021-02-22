package ru.vincetti.test.cashpointssample.core.network

import com.google.gson.annotations.SerializedName

data class DepositPoints(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("payload")
    val payload: List<Payload>,
    @SerializedName("trackingId")
    val trackingId: String
)