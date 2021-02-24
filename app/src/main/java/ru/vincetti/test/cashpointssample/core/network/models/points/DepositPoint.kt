package ru.vincetti.test.cashpointssample.core.network.models.points

import com.google.gson.annotations.SerializedName

data class DepositPoint(
    @SerializedName("addressInfo")
    val addressInfo: String,
    @SerializedName("bankInfo")
    val bankInfo: String?,
    @SerializedName("externalId")
    val externalId: String,
    @SerializedName("fullAddress")
    val fullAddress: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("partnerName")
    val partnerName: String,
    @SerializedName("phones")
    val phones: String?,
    @SerializedName("verificationInfo")
    val verificationInfo: String,
    @SerializedName("workHours")
    val workHours: String?
)
