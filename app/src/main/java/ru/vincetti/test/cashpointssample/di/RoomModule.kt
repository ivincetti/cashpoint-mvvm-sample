package ru.vincetti.test.cashpointssample.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.vincetti.test.cashpointssample.core.database.AppDatabase
import ru.vincetti.test.cashpointssample.core.database.CashPointsDao
import ru.vincetti.test.cashpointssample.core.database.DailyLimitsDao
import ru.vincetti.test.cashpointssample.core.database.PartnersDao
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(appContext: Application): AppDatabase {
        return AppDatabase.newInstance(appContext)
    }

    @Singleton
    @Provides
    fun providesCashPointsDao(database: AppDatabase): CashPointsDao {
        return database.cashPointsDao()
    }

    @Singleton
    @Provides
    fun providesPartnersDao(database: AppDatabase): PartnersDao {
        return database.partnersDao()
    }

    @Singleton
    @Provides
    fun providesDailyLimitsDao(database: AppDatabase): DailyLimitsDao {
        return database.dailyLimitsDao()
    }
}
