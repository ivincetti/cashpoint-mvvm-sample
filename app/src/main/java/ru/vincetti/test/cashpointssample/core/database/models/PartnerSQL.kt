package ru.vincetti.test.cashpointssample.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partner")
data class PartnerSQL(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "partner_request_id")
    val partnerRequestId: Int,

    val description: String,

    val name: String,

    val picture: String,
)
