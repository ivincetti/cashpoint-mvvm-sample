package ru.vincetti.test.cashpointssample.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partner")
data class PartnerSQL(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "partner_name")
    val partnerName: String,

    @ColumnInfo(name = "partner_request_id")
    val partnerRequestId: Long,

    val description: String,

    val name: String,

    val picture: String,
)
