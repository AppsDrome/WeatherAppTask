package com.frapp.weatherforecast.di.modules

import androidx.lifecycle.ViewModel
import com.frapp.weatherforecast.di.AssistedSavedStateViewModelFactory
import com.frapp.weatherforecast.di.ViewModelKey
import com.frapp.weatherforecast.presentation.forecast.CurrentWeatherForecastViewModel
import com.frapp.weatherforecast.presentation.weatherDetailsTabDay.DayWeatherDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author by Daniel Didah on 1/18/22
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherForecastViewModel::class)
    abstract fun bindCurrentWeatherViewModel(currentWeatherFactory: CurrentWeatherForecastViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(DayWeatherDetailsViewModel::class)
    abstract fun bindDayWeatherDetailViewModel(dayWeatherDetailsViewModel: DayWeatherDetailsViewModel): ViewModel

}