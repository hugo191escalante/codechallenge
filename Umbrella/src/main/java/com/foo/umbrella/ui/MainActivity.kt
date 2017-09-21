package com.foo.umbrella.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.foo.umbrella.R
import com.foo.umbrella.data.ApiServicesProvider
import com.foo.umbrella.data.model.WeatherData

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.Result

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiServicesProvider = ApiServicesProvider(application)
        apiServicesProvider.weatherService.forecastForZipObservable("30339")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Result<WeatherData>> {
                    override fun onSubscribe(@NonNull d: Disposable) {
                        Log.d(TAG, "onSubscribe: ")
                    }

                    override fun onNext(@NonNull weatherDataResult: Result<WeatherData>) {
                        if (!weatherDataResult.isError) {
                            val body = weatherDataResult.response()!!.body()
                            Log.d(TAG, "onNext: " + body)

                            for (forecastCondition in body!!.forecast) {
                                Log.d(TAG, "onNext:" + forecastCondition)
                            }
                        }
                    }

                    override fun onError(@NonNull e: Throwable) {
                        Log.d(TAG, "onError: " + e)
                        e.printStackTrace()
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onComplete: ")
                    }
                })
    }

    companion object {
        private val TAG = "MainActivityTAG_"
    }
}
