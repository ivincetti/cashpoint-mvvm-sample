package ru.vincetti.test.cashpointssample.di

import dagger.Binds
import dagger.Module
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.core.storage.StorageImpl
import ru.vincetti.test.cashpointssample.core.storage.repo.*

@Module
abstract class StorageModule {

    @Binds
    abstract fun bindPointRepo(repo: PointsRepoImpl): PointsRepo

    @Binds
    abstract fun bindMutablePointRepo(repo: PointsRepoImpl): MutablePointsRepo

    @Binds
    abstract fun bindPartnerRepo(repo: PartnersRepoImpl): PartnersRepo

    @Binds
    abstract fun bindMutablePartnerRepo(repo: PartnersRepoImpl): MutablePartnersRepo

    @Binds
    abstract fun bindStorage(storage: StorageImpl): Storage
}
