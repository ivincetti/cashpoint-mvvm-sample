package ru.vincetti.test.cashpointssample.di

import dagger.Binds
import dagger.Module
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.core.storage.StorageImpl
import ru.vincetti.test.cashpointssample.models.MutablePointsModel
import ru.vincetti.test.cashpointssample.models.PointsModel
import ru.vincetti.test.cashpointssample.models.PointsModelImpl

@Module
abstract class StorageModule {

    @Binds
    abstract fun bindPointModel(model: PointsModelImpl): PointsModel

    @Binds
    abstract fun bindMutablePointModel(model: PointsModelImpl): MutablePointsModel

    @Binds
    abstract fun bindStorage(storage: StorageImpl): Storage
}
