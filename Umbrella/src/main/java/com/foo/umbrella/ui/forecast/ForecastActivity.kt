package com.foo.umbrella.ui.forecast

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.foo.umbrella.R
import com.foo.umbrella.UmbrellaApp
import com.foo.umbrella.data.model.entities.ForecastCondition
import kotlinx.android.synthetic.main.activity_forecast.*
import javax.inject.Inject

class ForecastActivity : AppCompatActivity(), ForecastContract.View {

    private val TAG = "MainActivityTAG_"

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        injectDependencies()
        initViews()

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
        // TODO: Handle errors to be human readable
        Snackbar.make(a_forecast_content, error, Snackbar.LENGTH_SHORT).show()
        println("ForecastActivityTAG_: showError:" + error)
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

    override fun showCurrent(city: String, temperature: Int, description: String, isCold: Boolean) {
        println("ForecastActivityTAG_: showCurrent() $city $temperature $description $isCold")

        a_forecast_city.text = city
        a_forecast_description.text = description
        a_forecast_temperature.text = temperature.toString()

        when (isCold) {
            true -> a_forecast_toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.weather_cool))
            false -> a_forecast_toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.weather_warm))
        }
    }

    override fun showForecast(forecastCondition: List<ForecastCondition>, isCelsius: Boolean) {
        println("ForecastActivityTAG_: showForecast() $forecastCondition $isCelsius")
    }

    private fun initViews() {
        setSupportActionBar(a_forecast_toolbar)
    }
}
