package com.foo.umbrella.base

/**
 * Created by user on 9/20/17.
 */
interface BaseView {
    fun showError(error: String)
    fun showProgress()
    fun hideProgress()
    fun injectDependencies()
}