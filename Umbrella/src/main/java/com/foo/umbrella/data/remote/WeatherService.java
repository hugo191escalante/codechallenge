package com.foo.umbrella.data.remote;

import com.foo.umbrella.BuildConfig;
import com.foo.umbrella.data.model.entities.WeatherData;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit interface for fetching weather data
 */
public interface WeatherService {

    /**
     * Get the forecast for a given zip code using {@link Call}
     */
    @GET("/api/" + BuildConfig.API_KEY + "/conditions/hourly/q/{zip}.json")
    Call<WeatherData> forecastForZipCallable(@Path("zip") String zipCode);

    /**
     * Get the forecast for a given zip code using {@link Observable}
     */
    @GET("/api/" + BuildConfig.API_KEY + "/conditions/hourly/q/{zip}.json")
    Observable<Result<WeatherData>> forecastForZipResult(@Path("zip") String zipCode);

    /**
     * Get the plain forecast for a given zip code using {@link Observable}
     */
    @GET("/api/" + BuildConfig.API_KEY + "/conditions/hourly/q/{zip}.json")
    Observable<WeatherData> forecastForZipObservable(@Path("zip") String zipCode);
}
