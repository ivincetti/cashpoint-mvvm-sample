package ru.vincetti.test.cashpointssample.core.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partners_request")
data class PartnerRequest(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val date: Long
)
