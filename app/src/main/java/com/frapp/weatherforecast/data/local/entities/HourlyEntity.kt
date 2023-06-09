package com.frapp.weatherforecast.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frapp.weatherforecast.data.remote.dto.Weather


@Entity
data class HourlyEntity(
    val dt: Int,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val weather: Weather?,
    val windSpeed: Double,
    val timezoneOffSet: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
