package com.foo.umbrella.base

interface BasePresenter<in T : BaseView> {
    fun attachView(view: T)
    fun detachView()
}