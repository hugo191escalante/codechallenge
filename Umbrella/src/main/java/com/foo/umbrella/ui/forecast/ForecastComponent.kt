package com.foo.umbrella.ui.forecast

import com.foo.umbrella.di.UmbrellaComponent
import com.foo.umbrella.di.scopes.ViewScope
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(UmbrellaComponent::class), modules = arrayOf(ForecastModule::class))
interface ForecastComponent {
    fun inject(forecastActivity: ForecastActivity)
}