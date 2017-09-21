package com.foo.umbrella.data

import com.foo.umbrella.data.model.entities.WeatherData
import io.reactivex.Observable

interface WeatherDataSource {
    fun loadForecast(zipCode: String): Observable<WeatherData>
    fun saveForecast(weatherData: WeatherData)
}