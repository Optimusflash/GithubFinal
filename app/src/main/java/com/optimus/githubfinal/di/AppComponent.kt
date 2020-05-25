package com.optimus.githubfinal.di

import com.optimus.githubfinal.repository.MainRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */

@Component(modules = ([RemoteModule::class]))
@Singleton
interface AppComponent {
    fun inject(mainRepository: MainRepository)
}