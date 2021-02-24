package ru.vincetti.test.cashpointssample.di

import dagger.Binds
import dagger.Module
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.core.storage.StorageImpl
import javax.inject.Singleton

@Module
abstract class StorageModule {

    @Singleton
    @Binds
    abstract fun bindStorage(storage: StorageImpl): Storage
}
