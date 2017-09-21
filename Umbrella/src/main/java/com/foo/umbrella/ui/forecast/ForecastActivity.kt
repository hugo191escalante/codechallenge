package com.foo.umbrella.ui.forecast

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.foo.umbrella.R
import com.foo.umbrella.UmbrellaApp
import com.foo.umbrella.data.model.entities.CurrentObservation
import com.foo.umbrella.data.model.entities.ForecastCondition
import javax.inject.Inject

class ForecastActivity : AppCompatActivity(), ForecastContract.View {

    private val TAG = "MainActivityTAG_"

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()

        forecastPresenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()

        forecastPresenter.loadForecast()
    }

    override fun onDestroy() {
        super.onDestroy()
        forecastPresenter.detachView()
    }

    override fun showError(error: String) {
        println("ForecastActivityTAG_: showError:")
    }

    override fun showProgress() {
        println("ForecastActivityTAG_: showProgress:")
    }

    override fun hideProgress() {
        println("ForecastActivityTAG_: hideProgress:")
    }

    override fun injectDependencies() {
        val umbrellaComponent = (application as UmbrellaApp).umbrellaComponent

        DaggerForecastComponent.builder()
                .umbrellaComponent(umbrellaComponent)
                .forecastModule(ForecastModule())
                .build()
                .inject(this)
    }

    override fun showCurrent(currentObservation: CurrentObservation) {
        println("ForecastActivityTAG_: showCurrent() " + currentObservation)
    }

    override fun showForecast(forecastCondition: List<ForecastCondition>) {
        println("ForecastActivityTAG_: showForecast() " + forecastCondition)
    }


}
