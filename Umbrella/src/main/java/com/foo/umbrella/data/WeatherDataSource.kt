package com.foo.umbrella.data

import com.foo.umbrella.data.model.model.WeatherData
import io.reactivex.Flowable

/**
 * Created by user on 9/20/17.
 */

interface WeatherDataSource {
    fun loadForecast(): Flowable<WeatherData>
    fun saveForecast(weatherData: WeatherData)
}