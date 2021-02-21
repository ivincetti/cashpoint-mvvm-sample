package ru.vincetti.test.cashpointssample

import android.app.Application
import ru.vincetti.test.cashpointssample.di.DaggerApplicationComponent

class App : Application() {

    val appComponent = DaggerApplicationComponent.create()
}