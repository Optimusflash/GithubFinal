package com.optimus.githubfinal.di

import android.content.Context
import androidx.room.Room
import com.optimus.githubfinal.storage.GitRepoDao
import com.optimus.githubfinal.storage.GitRepoDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 26.05.2020.
 */

@Module
class StorageModule(private val applicationContext: Context) {
    @Provides
    @Singleton
    fun provideDatabase(): GitRepoDataBase {
        return Room.databaseBuilder(applicationContext, GitRepoDataBase::class.java, "github_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(gitRepoDataBase: GitRepoDataBase): GitRepoDao {
        return gitRepoDataBase.gitRepoDao()
    }
}