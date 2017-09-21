package com.foo.umbrella.ui.forecast

import com.foo.umbrella.base.BasePresenter
import com.foo.umbrella.base.BaseView
import com.foo.umbrella.data.model.entities.ForecastCondition

/**
 * Created by user on 9/21/17.
 */

interface ForecastContract {
    interface View : BaseView {
        fun showCurrent(city: String, temperature: Int, description: String, isCold: Boolean)
        fun showForecast(forecastCondition: List<ForecastCondition>, isCelsius: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun loadForecast()
    }
}