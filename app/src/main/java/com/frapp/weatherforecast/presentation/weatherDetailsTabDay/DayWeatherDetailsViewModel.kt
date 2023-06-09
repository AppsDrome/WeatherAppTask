package com.frapp.weatherforecast.presentation.weatherDetailsTabDay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frapp.weatherforecast.data.local.entities.DailyEntity
import com.frapp.weatherforecast.data.local.entities.HourlyEntity
import com.frapp.weatherforecast.domain.usecases.FiveDayForecast
import com.frapp.weatherforecast.domain.usecases.TodaysForecast
import com.frapp.weatherforecast.domain.usecases.TomorrowForecast
import com.frapp.weatherforecast.presentation.forecast.WeatherTabTypes
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class DayWeatherDetailsViewModel @Inject constructor(
    private val todaysForecast: TodaysForecast,
    private val tomorrowForecast: TomorrowForecast,
    private val fiveDayForecast: FiveDayForecast
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val dailyWeather = MutableLiveData<List<DailyEntity>>()
    val hourlyWeather = MutableLiveData<List<HourlyEntity>>()

    fun setWeatherTabType(weatherTabTypes: WeatherTabTypes) {
        when (weatherTabTypes) {
            WeatherTabTypes.TODAY -> {
                compositeDisposable.add(
                    todaysForecast.getForecast()
                        .subscribeOn(Schedulers.io())
                        .subscribe(hourlyWeather::postValue, Timber::e)
                )
            }
            WeatherTabTypes.TOMORROW -> {
                compositeDisposable.add(
                    tomorrowForecast.getForecast()
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            Collections.reverse(it)
                            hourlyWeather.postValue(it)
                        }, Timber::e)
                )
            }
            WeatherTabTypes.LATTER -> {
                compositeDisposable.add(
                    fiveDayForecast.getForecast()
                        .subscribeOn(Schedulers.io())
                        .subscribe(dailyWeather::postValue, Timber::e)
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}