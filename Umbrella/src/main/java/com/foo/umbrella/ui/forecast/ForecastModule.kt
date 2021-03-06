package com.foo.umbrella.ui.forecast

import com.foo.umbrella.data.WeatherRepository
import com.foo.umbrella.di.scopes.ViewScope
import dagger.Module
import dagger.Provides

@Module
class ForecastModule {

    @ViewScope
    @Provides
    fun provideForecastPresenter(weatherRepository: WeatherRepository): ForecastPresenter {
        return ForecastPresenter(weatherRepository)
    }
}