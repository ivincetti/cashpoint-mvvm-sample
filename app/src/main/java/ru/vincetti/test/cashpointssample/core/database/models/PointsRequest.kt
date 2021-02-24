package ru.vincetti.test.cashpointssample.core.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "points_request")
data class PointsRequest(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val date: Long,

    val radius: Long,

    val latitude: Long,

    val longitude: Long,

    val zoom: Float
)
