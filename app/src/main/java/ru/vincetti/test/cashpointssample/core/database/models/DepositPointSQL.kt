package ru.vincetti.test.cashpointssample.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "points")
data class DepositPointSQL(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val externalId: String,

    @ColumnInfo(name = "points_request_id")
    val pointsRequestId: Long,

    @ColumnInfo(name = "full_address")
    val fullAddress: String,

    val latitude: Double,
    val longitude: Double,

    @ColumnInfo(name = "partner_name")
    val partnerName: String,

    val phones: String,

    val workHours: String
)
