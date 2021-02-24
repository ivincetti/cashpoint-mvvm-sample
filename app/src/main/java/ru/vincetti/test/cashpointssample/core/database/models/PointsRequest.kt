package ru.vincetti.test.cashpointssample.core.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "points_request")
data class PointsRequest(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val date: Long,

    val radius: Long,

    val latitude: Double,

    val longitude: Double,
)
