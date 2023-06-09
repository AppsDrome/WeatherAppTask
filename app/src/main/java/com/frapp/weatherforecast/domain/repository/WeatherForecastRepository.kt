package com.frapp.weatherforecast.domain.repository

import com.frapp.weatherforecast.data.local.entities.CurrentEntity
import com.frapp.weatherforecast.data.local.entities.DailyEntity
import com.frapp.weatherforecast.data.local.entities.HourlyEntity
import com.frapp.weatherforecast.data.local.entities.LocationEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


interface WeatherForecastRepository {

    fun searchByCityName(cityName: String): Completable

    fun getCurrentForecast(): Observable<CurrentEntity>

    fun getCurrentLocation(): Observable<LocationEntity>

    fun getTodaysForecast(): Observable<List<HourlyEntity>>

    fun getTomorrowsForecast(): Observable<List<HourlyEntity>>

    fun getFiveDaysForecast(): Observable<List<DailyEntity>>

}