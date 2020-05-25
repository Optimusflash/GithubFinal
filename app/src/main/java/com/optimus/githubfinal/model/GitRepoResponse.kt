package com.optimus.githubfinal.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */
data class GitRepoResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val gitRepositories: List<GitRepository>
) {

}