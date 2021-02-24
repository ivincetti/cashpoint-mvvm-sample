package ru.vincetti.test.cashpointssample.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_limits")
data class DailyLimitSQL(

    @PrimaryKey
    val id: String,

    val amount: Int,

    @ColumnInfo(name = "currency_name")
    val currencyName: String
)
