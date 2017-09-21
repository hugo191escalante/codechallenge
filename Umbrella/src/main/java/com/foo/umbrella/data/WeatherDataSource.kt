package com.foo.umbrella.data

import com.foo.umbrella.data.model.entities.WeatherData
import io.reactivex.Observable

/**
 * Created by user on 9/20/17.
 */

interface WeatherDataSource {
    fun loadForecast(zipCode: String): Observable<WeatherData>
    fun saveForecast(weatherData: WeatherData)
}