package com.frapp.weatherforecast.di.components

import android.app.Application
import com.frapp.weatherforecast.App
import com.frapp.weatherforecast.di.modules.ApiServiceModule
import com.frapp.weatherforecast.di.modules.CommonUiModule
import com.frapp.weatherforecast.di.modules.WeatherDatabaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [ApiServiceModule::class, AppSubComponents::class,
        WeatherDatabaseModule::class, CommonUiModule::class]
)
@Singleton
interface AppComponent {

    fun getActivityComponentFactory(): ActivitySubComponent.Factory
    fun getFragmentComponentFactory(): FragmentSubComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}