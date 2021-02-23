package ru.vincetti.test.cashpointssample.di

import dagger.Module
import dagger.Provides
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.mvvm.PointViewModelFactory

@Module
class PointModule {

    @Provides
    fun providePointViewModelFactory(storage: Storage) = PointViewModelFactory(storage)
}
