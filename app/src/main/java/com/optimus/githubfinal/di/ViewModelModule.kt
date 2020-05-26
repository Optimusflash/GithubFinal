package com.optimus.githubfinal.di

import androidx.lifecycle.ViewModel
import com.optimus.githubfinal.viewmodels.DetailViewModel
import com.optimus.githubfinal.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Dmitriy Chebotar on 26.05.2020.
 */

@Module
abstract class ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(DetailViewModel::class)
    abstract fun provideDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}