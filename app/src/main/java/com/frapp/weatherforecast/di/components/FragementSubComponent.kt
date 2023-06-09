package com.frapp.weatherforecast.di.components

import com.frapp.weatherforecast.di.FragmentScope
import com.frapp.weatherforecast.di.modules.ViewModelModule
import com.frapp.weatherforecast.presentation.forecast.CurrentWeatherForecastFragment
import com.frapp.weatherforecast.presentation.weatherDetailsTabDay.DayWeatherDetailsFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [ViewModelModule::class])
interface FragmentSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentSubComponent
    }

    fun inject(currentWeatherForecastFragment: CurrentWeatherForecastFragment)
    fun inject(dayWeatherDetailsFragment: DayWeatherDetailsFragment)
}