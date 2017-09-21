package com.foo.umbrella.data.remote

import com.foo.umbrella.data.WeatherDataSource
import com.foo.umbrella.data.model.entities.WeatherData
import io.reactivex.Observable

class WeatherRemoteDataSource(private val weatherService: WeatherService) : WeatherDataSource {
    override fun loadForecast(zipCode: String): Observable<WeatherData> = weatherService.forecastForZipObservable(zipCode)
    override fun saveForecast(weatherData: WeatherData) {}
}