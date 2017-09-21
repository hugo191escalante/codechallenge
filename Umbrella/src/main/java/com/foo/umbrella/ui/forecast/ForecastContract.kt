package com.foo.umbrella.ui.forecast

import com.foo.umbrella.base.BasePresenter
import com.foo.umbrella.base.BaseView
import com.foo.umbrella.data.model.entities.CurrentObservation
import com.foo.umbrella.data.model.entities.ForecastCondition

/**
 * Created by user on 9/21/17.
 */

interface ForecastContract {
    interface View : BaseView {
        fun showCurrent(currentObservation: CurrentObservation)
        fun showForecast(forecastCondition: List<ForecastCondition>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadForecast()
    }
}