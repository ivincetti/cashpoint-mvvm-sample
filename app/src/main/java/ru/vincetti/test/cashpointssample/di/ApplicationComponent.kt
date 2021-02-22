package ru.vincetti.test.cashpointssample.di

import dagger.Component
import ru.vincetti.test.cashpointssample.ui.map.MapFragment
import ru.vincetti.test.cashpointssample.ui.point.PointFragment

@Component(
    modules = [ListModule::class]
)
interface ApplicationComponent {

    fun inject(fragment: MapFragment)

    fun inject(fragment: PointFragment)
}