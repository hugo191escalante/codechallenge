package com.foo.umbrella.data

import com.foo.umbrella.data.local.WeatherLocalDataSource
import com.foo.umbrella.data.model.entities.WeatherData
import com.foo.umbrella.data.remote.WeatherRemoteDataSource
import io.reactivex.Observable

class WeatherRepository(private val weatherLocalDataSource: WeatherLocalDataSource, private val weatherRemoteDataSource: WeatherRemoteDataSource) : WeatherDataSource {

    // TODO: Add logic for local repository
    override fun loadForecast(zipCode: String): Observable<WeatherData> = weatherRemoteDataSource.loadForecast(zipCode)

    override fun saveForecast(weatherData: WeatherData) {}
}