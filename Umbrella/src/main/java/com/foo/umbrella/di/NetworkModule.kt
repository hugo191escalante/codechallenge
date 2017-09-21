package com.foo.umbrella.di

import android.content.Context
import com.foo.umbrella.BuildConfig
import com.foo.umbrella.data.remote.WeatherService
import com.foo.umbrella.data.model.parsing.ForecastConditionAdapter
import com.foo.umbrella.data.model.parsing.MoshiAdapterFactory
import com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import javax.inject.Singleton

/**
 * Created by user on 9/20/17.
 */

@Module
class NetworkModule {

    private val DISK_CACHE_SIZE = MEGABYTES.toBytes(50).toInt()
    private val DISK_CACHE_FILENAME = "http"

    @Singleton
    @Provides
    fun provideCache(applicationContext: Context): Cache {
        val cacheDir = File(applicationContext.cacheDir, DISK_CACHE_FILENAME)
        return Cache(cacheDir, DISK_CACHE_SIZE.toLong())
    }

    @Singleton
    @Provides
    fun provideOkHttp(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(ForecastConditionAdapter())
                .add(MoshiAdapterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun providePicasso(applicationContext: Context, okHttpClient: OkHttpClient): Picasso {
        return Picasso.Builder(applicationContext)
                .downloader(OkHttp3Downloader(okHttpClient))
                .listener { _, uri, e -> Timber.e(e, "Failed to load image: %s", uri) }
                .build();
    }
}