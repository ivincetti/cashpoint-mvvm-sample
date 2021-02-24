package ru.vincetti.test.cashpointssample.core.network.models.partners

import com.google.gson.annotations.SerializedName

data class Partner(
    @SerializedName("brand")
    val brand: Brand?,
    @SerializedName("dailyLimits")
    val dailyLimits: List<DailyLimit>?,
    @SerializedName("depositionDuration")
    val depositionDuration: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("externalPartnerId")
    val externalPartnerId: String?,
    @SerializedName("hasLocations")
    val hasLocations: Boolean?,
    @SerializedName("hasPreferentialDeposition")
    val hasPreferentialDeposition: Boolean?,
    @SerializedName("id")
    val id: String,
    @SerializedName("isMomentary")
    val isMomentary: Boolean?,
    @SerializedName("limitations")
    val limitations: String?,
    @SerializedName("limits")
    val limits: List<Limit>?,
    @SerializedName("moneyMax")
    val moneyMax: Int?,
    @SerializedName("moneyMin")
    val moneyMin: Int?,
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("pointType")
    val pointType: String?,
    @SerializedName("url")
    val url: String?
)
