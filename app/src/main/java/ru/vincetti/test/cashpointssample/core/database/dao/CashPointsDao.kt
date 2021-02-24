package ru.vincetti.test.cashpointssample.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.vincetti.test.cashpointssample.core.database.models.DepositPointSQL

@Dao
interface CashPointsDao {

    @Insert
    suspend fun insertPoint(point: DepositPointSQL)

    @Insert
    suspend fun insertPoints(points: List<DepositPointSQL>)

    @Query("Delete FROM points")
    suspend fun deleteAllPoints()
}
