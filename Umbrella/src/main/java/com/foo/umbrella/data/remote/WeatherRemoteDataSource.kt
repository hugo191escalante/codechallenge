package com.foo.umbrella.data.remote

import com.foo.umbrella.data.WeatherDataSource
import com.foo.umbrella.data.model.entities.WeatherData
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by user on 9/21/17.
 */
class WeatherRemoteDataSource(private val weatherService: WeatherService) : WeatherDataSource {
    override fun loadForecast(zipCode: String): Observable<WeatherData> = weatherService.forecastForZipObservable(zipCode)
    override fun saveForecast(weatherData: WeatherData) {}
}