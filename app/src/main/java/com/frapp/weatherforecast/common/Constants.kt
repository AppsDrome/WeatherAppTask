package com.frapp.weatherforecast.common

import com.frapp.weatherforecast.BuildConfig


object Constants {
    const val WEATHER_FORECAST_END_POINT = "https://api.openweathermap.org/data/2.5/"
    const val API_KEY = BuildConfig.API_KEY
    const val GEOCODING_END_POINT = "http://api.openweathermap.org/geo/1.0/"
    const val WEATHER_API = "WEATHER_API"
    const val GEOCODING_API = "GEOCODING_API"
    const val WEATHER_ICON_URL = "http://openweathermap.org/img/wn/%s@2x.png"
    const val WEATHER_DATABASE_NAME = "Weather"
}