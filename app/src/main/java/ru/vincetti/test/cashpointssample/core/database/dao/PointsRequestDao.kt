package ru.vincetti.test.cashpointssample.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.vincetti.test.cashpointssample.core.database.models.PointsRequest

@Dao
interface PointsRequestDao {

    @Insert
    suspend fun insertRequest(request: PointsRequest): Long

    @Query("Delete FROM points_request")
    suspend fun deleteAll()

    @Query("Delete FROM points_request WHERE id = :requestID")
    suspend fun deleteById(requestID: Long)

    @Query(
        """
        Select * FROM points_request 
        WHERE radius = :radius AND latitude = :latitude AND longitude = :longitude 
        ORDER BY id DESC LIMIT 1
        """
    )
    suspend fun getDate(
        latitude: Double,
        longitude: Double,
        radius: Long
    ): PointsRequest?
}
