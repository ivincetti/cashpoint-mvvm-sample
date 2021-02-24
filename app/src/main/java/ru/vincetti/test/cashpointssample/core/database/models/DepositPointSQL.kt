package ru.vincetti.test.cashpointssample.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "points")
data class DepositPointSQL(

    @PrimaryKey
    val externalId: String,

    @ColumnInfo(name = "points_request_id")
    val pointsRequestId: Int,

    @ColumnInfo(name = "full_address")
    val fullAddress: String,

    val latitude: Long,
    val longitude: Long,

    @ColumnInfo(name = "partner_name")
    val partnerName: String,

    val phones: String,

    val workHours: String
)
