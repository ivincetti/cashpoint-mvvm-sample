package ru.vincetti.test.cashpointssample.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.vincetti.test.cashpointssample.models.ListViewModelFactory
import ru.vincetti.test.cashpointssample.models.PointsModel
import ru.vincetti.test.cashpointssample.models.PointsModelImpl

@Module
abstract class ListModule {

    @Binds
    abstract fun bindPointModel(model: PointsModelImpl): PointsModel

    @Module
    companion object {

        @Provides
        fun provideListViewModelFactory(model: PointsModel) = ListViewModelFactory(model)
    }
}
