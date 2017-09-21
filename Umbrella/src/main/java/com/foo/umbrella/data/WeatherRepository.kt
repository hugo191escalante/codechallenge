package com.foo.umbrella.data

import com.foo.umbrella.data.local.WeatherLocalDataSource
import com.foo.umbrella.data.model.entities.WeatherData
import com.foo.umbrella.data.remote.WeatherRemoteDataSource
import io.reactivex.Observable

/**
 * Created by user on 9/21/17.
 */

class WeatherRepository(val weatherLocalDataSource: WeatherLocalDataSource, val weatherRemoteDataSource: WeatherRemoteDataSource) : WeatherDataSource {

    // TODO: Add logic for local repository
    override fun loadForecast(zipCode: String): Observable<WeatherData> = weatherLocalDataSource.loadForecast(zipCode)

    override fun saveForecast(weatherData: WeatherData) {}
}