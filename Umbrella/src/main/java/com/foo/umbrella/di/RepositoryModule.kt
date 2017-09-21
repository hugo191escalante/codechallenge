package com.foo.umbrella.di

import com.foo.umbrella.data.WeatherRepository
import com.foo.umbrella.data.local.WeatherLocalDataSource
import com.foo.umbrella.data.remote.WeatherRemoteDataSource
import com.foo.umbrella.data.remote.WeatherService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherLocalDataSource(): WeatherLocalDataSource {
        return WeatherLocalDataSource() //database, DAO, Room
    }

    @Singleton
    @Provides
    fun provideWeatherRemoteDataSource(weatherService: WeatherService): WeatherRemoteDataSource {
        return WeatherRemoteDataSource(weatherService)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(localDataSource: WeatherLocalDataSource, remoteDataSource: WeatherRemoteDataSource): WeatherRepository {
        return WeatherRepository(localDataSource, remoteDataSource)
    }
}