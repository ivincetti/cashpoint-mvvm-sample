package ru.vincetti.test.cashpointssample.di

import dagger.Module
import dagger.Provides
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.models.ListViewModelFactory

@Module
class ListModule {

    @Provides
    fun provideListViewModelFactory(storage: Storage) = ListViewModelFactory(storage)
}
