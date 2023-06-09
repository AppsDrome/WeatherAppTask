package com.frapp.weatherforecast.domain.usecases

import com.frapp.weatherforecast.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject


class SearchForecast @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) {

    fun byCityNameForecast(cityName: String): Completable {
        return weatherForecastRepository.searchByCityName(cityName)
    }

}