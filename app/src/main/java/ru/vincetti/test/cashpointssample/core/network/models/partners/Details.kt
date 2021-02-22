package ru.vincetti.test.cashpointssample.core.network.models.partners

import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("hasNext")
    val hasNext: Boolean?
)
