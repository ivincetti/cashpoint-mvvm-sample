package ru.vincetti.test.cashpointssample.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.vincetti.test.cashpointssample.core.database.dao.*
import ru.vincetti.test.cashpointssample.core.database.models.*

@Database(
    entities = [
        PartnerSQL::class,
        DepositPointSQL::class,
        PointsRequest::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cashPointsDao(): CashPointsDao
    abstract fun partnersDao(): PartnersDao
    abstract fun pointsRequestsDao(): PointsRequestDao
    abstract fun pointsDao(): PointsDao

    companion object {

        private const val DB_NAME = "cashpoint.db"

        fun newInstance(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}
