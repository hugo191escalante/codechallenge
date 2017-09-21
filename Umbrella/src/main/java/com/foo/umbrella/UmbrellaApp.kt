package com.foo.umbrella

import android.app.Application
import com.foo.umbrella.di.*

import com.jakewharton.threetenabp.AndroidThreeTen

class UmbrellaApp : Application() {

    var umbrellaComponent: UmbrellaComponent? = null

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        umbrellaComponent = DaggerUmbrellaComponent.builder()
                .umbrellaModule(UmbrellaModule(this))
                .networkModule(NetworkModule())
                .repositoryModule(RepositoryModule())
                .build()
    }
}
