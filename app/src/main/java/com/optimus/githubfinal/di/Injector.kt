package com.optimus.githubfinal.di

import android.content.Context

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */
class Injector {
    companion object{
        private lateinit var appComponent: AppComponent

        fun initComponent(applicationContext: Context){
            appComponent = DaggerAppComponent.create()
        }

        fun getAppComponent() = appComponent
    }

}