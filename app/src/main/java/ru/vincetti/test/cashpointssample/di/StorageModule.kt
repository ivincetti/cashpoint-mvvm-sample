package ru.vincetti.test.cashpointssample.di

import dagger.Binds
import dagger.Module
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.core.storage.StorageImpl
import ru.vincetti.test.cashpointssample.models.*

@Module
abstract class StorageModule {

    @Binds
    abstract fun bindPointModel(model: PointsModelImpl): PointsModel

    @Binds
    abstract fun bindMutablePointModel(model: PointsModelImpl): MutablePointsModel

    @Binds
    abstract fun bindPartnerModel(model: PartnersModelImpl): PartnersModel

    @Binds
    abstract fun bindMutablePartnerModel(model: PartnersModelImpl): MutablePartnersModel

    @Binds
    abstract fun bindStorage(storage: StorageImpl): Storage
}
