package com.foo.umbrella.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UmbrellaModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return application.applicationContext
    }
}