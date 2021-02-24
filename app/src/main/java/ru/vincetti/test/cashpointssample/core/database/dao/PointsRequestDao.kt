package ru.vincetti.test.cashpointssample.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.vincetti.test.cashpointssample.core.database.models.PointsRequest

@Dao
interface PointsRequestDao {

    @Insert
    suspend fun insertRequest(request: PointsRequest)

    @Query("Delete FROM points_request")
    suspend fun deleteAllRequests()
}
