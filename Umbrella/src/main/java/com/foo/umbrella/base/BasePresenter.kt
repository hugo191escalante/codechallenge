package com.foo.umbrella.base

/**
 * Created by user on 9/20/17.
 */
interface BasePresenter<in T : BaseView> {
    fun attachView(view: T)
    fun detachView()
}