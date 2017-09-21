package com.foo.umbrella.ui.forecast

import com.foo.umbrella.data.WeatherRepository
import com.foo.umbrella.data.model.entities.WeatherData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * Created by user on 9/21/17.
 */
class ForecastPresenter(private val weatherRepository: WeatherRepository) : ForecastContract.Presenter {

    var view: ForecastContract.View? = null

    private val compositeDisposable: CompositeDisposable? = CompositeDisposable()

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
        view?.showCurrent(weatherData.currentObservation)
        view?.showForecast(weatherData.forecast)
    }
}