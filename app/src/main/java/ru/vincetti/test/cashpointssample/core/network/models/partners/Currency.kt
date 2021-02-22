package ru.vincetti.test.cashpointssample.core.network.models.partners

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("strCode")
    val strCode: String?
)
