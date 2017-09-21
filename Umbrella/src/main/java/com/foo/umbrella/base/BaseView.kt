package com.foo.umbrella.base

interface BaseView {
    fun showError(error: String)
    fun showProgress()
    fun hideProgress()
    fun injectDependencies()
}