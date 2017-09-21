package com.foo.umbrella.data.local

import com.foo.umbrella.data.WeatherDataSource
import com.foo.umbrella.data.model.entities.WeatherData
import io.reactivex.Observable

/**
 * Created by user on 9/21/17.
 */
class WeatherLocalDataSource : WeatherDataSource {
    override fun loadForecast(zipCode: String): Observable<WeatherData> = Observable.never()
    override fun saveForecast(weatherData: WeatherData) {}
}