package com.optimus.githubfinal.di

import com.optimus.githubfinal.repository.MainRepository
import com.optimus.githubfinal.ui.activities.MainActivity
import com.optimus.githubfinal.ui.fragments.RepoDetailFragment
import com.optimus.githubfinal.ui.fragments.RepoListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */

@Component(modules = ([RemoteModule::class, ViewModelModule::class]))
@Singleton
interface AppComponent {
    fun inject(mainRepository: MainRepository)

    fun inject(mainActivity: MainActivity)
    fun inject(repoListFragment: RepoListFragment)
    fun inject(repoDetailFragment: RepoDetailFragment)
}