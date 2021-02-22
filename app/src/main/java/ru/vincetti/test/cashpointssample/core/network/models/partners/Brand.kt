package ru.vincetti.test.cashpointssample.core.network.models.partners

import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("baseColor")
    val baseColor: String?,
    @SerializedName("baseTextColor")
    val baseTextColor: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("logoFile")
    val logoFile: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("roundedLogo")
    val roundedLogo: Boolean?
)
