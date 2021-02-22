package ru.vincetti.test.cashpointssample.core.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.vincetti.test.cashpointssample.core.network.models.partners.Partners
import ru.vincetti.test.cashpointssample.core.network.models.points.DepositPoints

interface TinkoffService {

    @GET("deposition_points")
    suspend fun getDepositPoints(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Long
    ): DepositPoints

    @GET("deposition_partners?accountType=Credit")
    suspend fun getPartners(): Partners
}

const val DOWNLOAD_OK = "OK"
