package com.optimus.githubfinal.di

import android.app.Application

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        Injector.initComponent(this)
    }
}