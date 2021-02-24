package ru.vincetti.test.cashpointssample.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.vincetti.test.cashpointssample.core.data.CashPointDetails
import ru.vincetti.test.cashpointssample.core.database.models.DepositPointSQL
import ru.vincetti.test.cashpointssample.core.database.models.PartnerSQL

@Dao
interface PointsDao {

    @Query(
        """ 
        Select p1.externalId as id, p2.name, p1.latitude, p1.longitude, p2.picture as image, 
        p1.full_address as address, p1.workHours, p1.phones, p2.description
        FROM points as p1
        JOIN partner p2 ON p2.partner_name = p1.partner_name
        WHERE points_request_id = :requestID
    """
    )
    suspend fun getAll(requestID: Long): List<CashPointDetails>?

    @Query(
        """ 
        Select p1.externalId as id, p2.name, p1.latitude, p1.longitude, p2.picture as image,
        p1.full_address as address, p1.workHours, p1.phones, p2.description
        FROM points as p1
        JOIN partner p2 ON p2.partner_name = p1.partner_name
        WHERE p1.externalId = :id
    """
    )
    suspend fun  getPointById(id: String): CashPointDetails?
}
