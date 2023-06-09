package com.frapp.weatherforecast.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.frapp.weatherforecast.data.local.dao.CurrentWeatherDao
import com.frapp.weatherforecast.data.local.dao.DailyWeatherDao
import com.frapp.weatherforecast.data.local.dao.HourlyWeatherDao
import com.frapp.weatherforecast.data.local.dao.LocationDao
import com.frapp.weatherforecast.data.local.entities.CurrentEntity
import com.frapp.weatherforecast.data.local.entities.DailyEntity
import com.frapp.weatherforecast.data.local.entities.HourlyEntity
import com.frapp.weatherforecast.data.local.entities.LocationEntity


@Database(
    entities = [CurrentEntity::class, DailyEntity::class, HourlyEntity::class, LocationEntity::class],
    version = 1
)
@TypeConverters(WeatherConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getCurrentWeatherDao(): CurrentWeatherDao
    abstract fun getDailyWeatherDao(): DailyWeatherDao
    abstract fun getHourlyWeatherDao(): HourlyWeatherDao
    abstract fun getLocationDao(): LocationDao
}