package com.frapp.weatherforecast.domain.usecases

import com.frapp.weatherforecast.data.local.entities.HourlyEntity
import com.frapp.weatherforecast.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class TodaysForecast @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) {

    fun getForecast(): Observable<List<HourlyEntity>> {
        return weatherForecastRepository.getTodaysForecast()
    }

}