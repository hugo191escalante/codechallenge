package com.foo.umbrella.ui.forecast

import com.foo.umbrella.data.WeatherRepository
import com.foo.umbrella.data.model.entities.CurrentObservation
import com.foo.umbrella.data.model.entities.ForecastCondition
import com.foo.umbrella.data.model.entities.WeatherData
import com.foo.umbrella.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ForecastPresenter(private val weatherRepository: WeatherRepository) : ForecastContract.Presenter {

    var view: ForecastContract.View? = null

    private val compositeDisposable: CompositeDisposable? = CompositeDisposable()
    private var weatherData: WeatherData? = null
    private var isCelsius: Boolean = false

    override fun attachView(view: ForecastContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        compositeDisposable?.dispose()
    }

    override fun loadForecast() {
        view?.showProgress()

        weatherRepository.loadForecast("94142")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ validateWeatherData(it) },
                        { view?.showError(it.toString()) },
                        { view?.hideProgress() },
                        { compositeDisposable?.add(it) })
    }

    private fun validateWeatherData(weatherData: WeatherData) {
        this.weatherData = weatherData

        generateHeader(weatherData.currentObservation)
        generateForecast(weatherData.forecast)
    }

    private fun generateForecast(forecast: MutableList<ForecastCondition>) {
        val forecastConditionDays = Utils.parseForecastConditions(forecast)
        view?.showForecast(forecastConditionDays, isCelsius)
    }

    private fun generateHeader(currentObservation: CurrentObservation) {
        val temperature: Int = when (isCelsius) {
            true -> currentObservation.tempCelsius.toDouble().toInt()
            false -> currentObservation.tempFahrenheit.toDouble().toInt()
        }
        val city = currentObservation.displayLocation.fullName
        val description = currentObservation.weatherDescription
        val isCold = currentObservation.tempFahrenheit.toDouble().toInt() < 60

        view?.showCurrent(city, temperature, description, isCold)
    }
}