package com.optimus.githubfinal.repository

import com.optimus.githubfinal.di.Injector
import com.optimus.githubfinal.model.GitRepository
import com.optimus.githubfinal.remote.GitApiService
import com.optimus.githubfinal.storage.GitRepoDao
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */

class MainRepository @Inject constructor(private val api: GitApiService, private val db: GitRepoDao) {


    fun loadGitRepositoriesFromApi(repoName: String)=api.getGitRepositories(repoName)
    fun loadGitRepositoryFromApi(user: String, repo: String) = api.getGitRepository(user, repo)

    fun loadGitRepositoriesFromDb()=db.getAll()
    fun loadGitRepositoryFromDb(user: String, repo: String) = db.getOne(user, repo)
    fun saveGitRepoToDb(repos: List<GitRepository>) = db.deleteAllAndInsert(repos)
}