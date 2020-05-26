package com.optimus.githubfinal.remote

import com.optimus.githubfinal.model.GitRepoResponse
import com.optimus.githubfinal.model.GitRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */

interface GitApiService {
    @GET("search/repositories")
    fun getGitRepositories(@Query("q") repoName: String): Single<GitRepoResponse>

    @GET("repos/{user}/{repo}")
    fun getGitRepository(@Path("user")user: String, @Path("repo")repo: String): Single<GitRepository>
}