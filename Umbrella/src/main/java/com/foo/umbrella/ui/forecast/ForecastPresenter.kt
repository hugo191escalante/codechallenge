package com.foo.umbrella.ui.forecast

import com.foo.umbrella.data.WeatherRepository
import com.foo.umbrella.data.model.entities.CurrentObservation
import com.foo.umbrella.data.model.entities.ForecastCondition
import com.foo.umbrella.data.model.entities.WeatherData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by user on 9/21/17.
 */
class ForecastPresenter(private val weatherRepository: WeatherRepository) : ForecastContract.Presenter {

    var view: ForecastContract.View? = null

    private val compositeDisposable: CompositeDisposable? = CompositeDisposable()
    private var weatherData: WeatherData? = null
    private var isCelsius: Boolean = true

    override fun attachView(view: ForecastContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        compositeDisposable?.dispose()
    }

    override fun loadForecast() {
        view?.showProgress()

        weatherRepository.loadForecast("30339")
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
        view?.showForecast(forecast, isCelsius)
    }

    private fun generateHeader(currentObservation: CurrentObservation) {
        val temperature = if (isCelsius) currentObservation.tempCelsius.toInt() else currentObservation.tempFahrenheit.toInt()
        val city = currentObservation.displayLocation.fullName
        val description = currentObservation.weatherDescription
        val isCold = currentObservation.tempCelsius.toInt() < 30

        view?.showCurrent(city, temperature, description, isCold)
    }
}