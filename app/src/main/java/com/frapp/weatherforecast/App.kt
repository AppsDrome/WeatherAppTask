package com.frapp.weatherforecast

import android.app.Application
import com.frapp.weatherforecast.common.TimberLoggingTree
import com.frapp.weatherforecast.di.components.AppComponent
import com.frapp.weatherforecast.di.components.DaggerAppComponent
import timber.log.Timber

/**
 * @author by Daniel Didah on 1/18/22
 */
class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(TimberLoggingTree())
        appComponent = DaggerAppComponent.builder()
            .application(this).build()
        appComponent.inject(this)
    }
}