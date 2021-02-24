package ru.vincetti.test.cashpointssample.di

import dagger.Component
import ru.vincetti.test.cashpointssample.ui.list.ListFragment
import ru.vincetti.test.cashpointssample.ui.map.MapFragment
import ru.vincetti.test.cashpointssample.ui.point.PointFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ListModule::class,
        PointModule::class,
        RetrofitModule::class,
        StorageModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: MapFragment)

    fun inject(fragment: ListFragment)

    fun inject(fragment: PointFragment)
}
