package ru.vincetti.test.cashpointssample.core.network.models.partners

import com.google.gson.annotations.SerializedName

data class Partners(
    @SerializedName("resultCode")
    val resultCode: String?,
    @SerializedName("details")
    val details: Details?,
    @SerializedName("payload")
    val partnersList: List<Partner>,
    @SerializedName("trackingId")
    val trackingId: String?
)
