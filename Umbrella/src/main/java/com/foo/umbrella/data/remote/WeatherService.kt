package com.foo.umbrella.data.remote

import com.foo.umbrella.BuildConfig
import com.foo.umbrella.data.model.entities.WeatherData

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit interface for fetching weather data
 */
interface WeatherService {

    /**
     * Get the forecast for a given zip code using [Call]
     */
    @GET("/api/" + BuildConfig.API_KEY + "/conditions/hourly/q/{zip}.json")
    fun forecastForZipCallable(@Path("zip") zipCode: String): Call<WeatherData>

    /**
     * Get the forecast for a given zip code using [Observable]
     */
    @GET("/api/" + BuildConfig.API_KEY + "/conditions/hourly/q/{zip}.json")
    fun forecastForZipResult(@Path("zip") zipCode: String): Observable<Result<WeatherData>>

    /**
     * Get the plain forecast for a given zip code using [Observable]
     */
    @GET("/api/" + BuildConfig.API_KEY + "/conditions/hourly/q/{zip}.json")
    fun forecastForZipObservable(@Path("zip") zipCode: String): Observable<WeatherData>
}
