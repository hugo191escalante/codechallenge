package com.foo.umbrella.ui.forecast

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.foo.umbrella.R
import com.foo.umbrella.data.ApiServicesProvider
import com.foo.umbrella.data.model.entities.WeatherData

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.Result

class ForecastActivity : AppCompatActivity() {

    private val TAG = "MainActivityTAG_"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
