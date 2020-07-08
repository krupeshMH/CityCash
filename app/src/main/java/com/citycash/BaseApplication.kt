package com.citycash

import android.app.Application
import com.citycash.di.AppComponent
import com.citycash.di.DaggerAppComponent

open class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    open fun initAppComponent() {
        appComponent = DaggerAppComponent
            .factory()
            .create(this)
    }


}