package com.frapp.weatherforecast.domain.usecases

import com.frapp.weatherforecast.data.local.entities.LocationEntity
import com.frapp.weatherforecast.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class GetCurrentForecastLocation @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) {

    fun getLocation(): Observable<LocationEntity> {
        return weatherForecastRepository.getCurrentLocation()
    }
}