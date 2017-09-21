package com.foo.umbrella.di

import com.foo.umbrella.data.WeatherRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(UmbrellaModule::class, NetworkModule::class, RepositoryModule::class))
interface UmbrellaComponent {
    fun weatherRepository(): WeatherRepository
}