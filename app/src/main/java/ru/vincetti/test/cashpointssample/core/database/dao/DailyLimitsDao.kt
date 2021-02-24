package ru.vincetti.test.cashpointssample.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.vincetti.test.cashpointssample.core.database.models.DailyLimitSQL

@Dao
interface DailyLimitsDao {

    @Insert
    suspend fun insertDailyLimit(limit: DailyLimitSQL)

    @Insert
    suspend fun insertDailyLimits(limits: List<DailyLimitSQL>)

    @Query("Delete FROM daily_limits")
    suspend fun deleteDailyLimits()
}
