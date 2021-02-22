package ru.vincetti.test.cashpointssample.core.network.models.points

import com.google.gson.annotations.SerializedName

data class DepositPoints(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("payload")
    val depositPointList: List<DepositPoint>,
    @SerializedName("trackingId")
    val trackingId: String
)
