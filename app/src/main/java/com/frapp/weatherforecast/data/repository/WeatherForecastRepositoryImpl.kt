package com.frapp.weatherforecast.data.repository

import com.frapp.weatherforecast.common.Constants
import com.frapp.weatherforecast.data.local.dao.CurrentWeatherDao
import com.frapp.weatherforecast.data.local.dao.DailyWeatherDao
import com.frapp.weatherforecast.data.local.dao.HourlyWeatherDao
import com.frapp.weatherforecast.data.local.dao.LocationDao
import com.frapp.weatherforecast.data.local.entities.CurrentEntity
import com.frapp.weatherforecast.data.local.entities.DailyEntity
import com.frapp.weatherforecast.data.local.entities.HourlyEntity
import com.frapp.weatherforecast.data.local.entities.LocationEntity
import com.frapp.weatherforecast.data.remote.api.GeocodingApi
import com.frapp.weatherforecast.data.remote.api.WeatherForecastApi
import com.frapp.weatherforecast.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class WeatherForecastRepositoryImpl @Inject constructor(
    private val geocodingApi: GeocodingApi,
    private val weatherForecastApi: WeatherForecastApi,
    private val currentWeatherDao: CurrentWeatherDao,
    private val dailyWeatherDao: DailyWeatherDao,
    private val hourlyWeatherDao: HourlyWeatherDao,
    private val locationDao: LocationDao
) : WeatherForecastRepository {


    override fun searchByCityName(cityName: String):Completable {
        return geocodingApi
            .getGeocoding(cityName, Constants.API_KEY)
            .map {
                it[0]
            }.switchMap { geocodeDetails ->
             locationDao.deleteAll()
                 .andThen(locationDao.addLocation(geocodeDetails.mapToLocationEntity()))
                 .andThen(weatherForecastApi
                    .getWeatherForecastByLatitudeLongitude(
                        geocodeDetails.lat.toString(),
                        geocodeDetails.lon.toString(),
                        "minutely",
                        Constants.API_KEY
                    ))
            }.flatMapCompletable { oneCallWeather ->
                currentWeatherDao.deleteAll()
                    .andThen(dailyWeatherDao.deleteAll())
                    .andThen(hourlyWeatherDao.deleteAll())
                   .andThen(currentWeatherDao.addCurrent(
                       oneCallWeather.current
                           .mapToCurrentEntity(oneCallWeather.timezoneOffset)
                )).andThen(dailyWeatherDao
                        .addAllDailyEntity(oneCallWeather.daily.map {
                    it.mapToDailyEntity(oneCallWeather.timezoneOffset)
                })).andThen(hourlyWeatherDao
                        .addAllHourlyEntity(oneCallWeather
                            .hourly.map { it.mapToHourlyEntity(oneCallWeather.timezoneOffset)
                       })
                )
            }
    }


    override fun getCurrentForecast(): Observable<CurrentEntity> {
        return currentWeatherDao.getCurrent()
    }

    override fun getCurrentLocation(): Observable<LocationEntity> {
        return locationDao.getCurrentLocation()
    }

    override fun getTodaysForecast(): Observable<List<HourlyEntity>> {
        return hourlyWeatherDao.getTodayHourlyWeather()
    }

    override fun getTomorrowsForecast(): Observable<List<HourlyEntity>> {
        return hourlyWeatherDao.getTomorrowHourlyWeather()
    }

    override fun getFiveDaysForecast(): Observable<List<DailyEntity>> {
        return dailyWeatherDao.getFirstFiveDailyEntity()
    }

}