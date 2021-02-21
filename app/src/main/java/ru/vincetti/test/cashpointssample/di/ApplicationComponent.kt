package ru.vincetti.test.cashpointssample.di

import dagger.Component
import ru.vincetti.test.cashpointssample.ui.map.MapFragment

@Component(
    modules = [ListModule::class]
)
interface ApplicationComponent {

    fun inject(fragment: MapFragment)
}