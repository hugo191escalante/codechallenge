package com.foo.umbrella.di

import dagger.Component
import javax.inject.Singleton

/**
 * Created by user on 9/20/17.
 */

@Singleton
@Component(modules = arrayOf(UmbrellaModule::class, NetworkModule::class, RepositoryModule::class))
interface UmbrellaComponent {

}