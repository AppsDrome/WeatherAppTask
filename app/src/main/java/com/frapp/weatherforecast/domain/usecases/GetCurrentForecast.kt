package com.frapp.weatherforecast.domain.usecases

import com.frapp.weatherforecast.data.local.entities.CurrentEntity
import com.frapp.weatherforecast.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class GetCurrentForecast @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) {

    fun getCurrentForecast(): Observable<CurrentEntity> {
        return weatherForecastRepository.getCurrentForecast()
    }
}