package com.optimus.githubfinal.repository

import com.optimus.githubfinal.di.Injector
import com.optimus.githubfinal.model.GitRepository
import com.optimus.githubfinal.remote.GitApiService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */

class MainRepository @Inject constructor(val api: GitApiService) {

    init {
        Injector.getAppComponent().inject(this)
    }

    fun loadGitRepositoriesFromApi(repoName: String)=api.getGitRepositories(repoName)
    fun loadGitRepositoryFromApi(user: String, repo: String) = api.getGitRepository(user, repo)
}