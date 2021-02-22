package ru.vincetti.test.cashpointssample.core.network

import retrofit2.http.GET
import retrofit2.http.Query

interface TinkoffService {

    @GET("deposition_points")
    suspend fun getDepositPoints(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Long
    ): DepositPoints
}

const val DOWNLOAD_OK = "OK"