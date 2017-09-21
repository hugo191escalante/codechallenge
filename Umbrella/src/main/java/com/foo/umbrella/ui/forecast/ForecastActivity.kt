package com.foo.umbrella.ui.forecast

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.foo.umbrella.R
import com.foo.umbrella.UmbrellaApp
import com.foo.umbrella.data.model.entities.ForecastConditionDay
import kotlinx.android.synthetic.main.activity_forecast.*
import javax.inject.Inject

class ForecastActivity : AppCompatActivity(), ForecastContract.View {

    private val TAG = "MainActivityTAG_"

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    private val forecasts: MutableList<ForecastConditionDay> = mutableListOf<ForecastConditionDay>()
    private var adapter: ForecastAdapter? = null

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.forecast_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.m_forecast_settings -> {
                newSettingsActivity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showError(error: String) {
        // TODO: Handle errors to be human readable
        Snackbar.make(a_forecast_content, error, Snackbar.LENGTH_SHORT).show()
        println("$TAG showError:" + error)
    }

    override fun showProgress() {
        // TODO: Add ProgressBar
        println("$TAG showProgress:")
    }

    override fun hideProgress() {
        // TODO: Hide ProgressBar
        println("$TAG hideProgress:")
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
        a_forecast_city.text = city
        a_forecast_description.text = description
        a_forecast_temperature.text = "$temperature °"

        when (isCold) {
            true -> a_forecast_toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.weather_cool))
            false -> a_forecast_toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.weather_warm))
        }
    }

    override fun showForecast(forecastDays: List<ForecastConditionDay>, isCelsius: Boolean) {
        forecasts.clear()
        forecasts.addAll(forecastDays)
        adapter?.notifyDataSetChanged()
    }

    private fun initViews() {
        setSupportActionBar(a_forecast_toolbar)

        adapter = ForecastAdapter(forecasts, true)

        a_forecast_recycler.layoutManager = LinearLayoutManager(this)
        a_forecast_recycler.adapter = adapter
    }

    private fun newSettingsActivity() {
        println("HEHEHE")
    }
}
