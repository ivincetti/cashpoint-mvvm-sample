package ru.vincetti.test.cashpointssample.core.database.dao

import androidx.room.*
import ru.vincetti.test.cashpointssample.core.database.models.DepositPointSQL
import ru.vincetti.test.cashpointssample.core.database.models.PartnerRequest

@Dao
interface PartnersRequestDao {

    @Insert
    suspend fun insertRequest(request: PartnerRequest)

    @Query("Delete FROM partners_request")
    suspend fun deleteAllRequests()
}
