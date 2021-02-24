package ru.vincetti.test.cashpointssample.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.vincetti.test.cashpointssample.core.database.models.PartnerSQL

@Dao
interface PartnersDao {

    @Insert
    suspend fun insertPoint(partner: PartnerSQL)

    @Insert
    suspend fun insertPoints(partners: List<PartnerSQL>)

    @Query("Delete FROM partner")
    suspend fun deleteAllPartners()
}
