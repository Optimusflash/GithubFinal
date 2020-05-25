package com.optimus.githubfinal.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */


data class GitRepository(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("created_at") val createdDate: Date,
    @SerializedName("updated_at") val updatedDate: Date,
    @SerializedName("stargazers_count") val starsCount: Int,
    @SerializedName("language") val progLanguage: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("owner") val owner: Owner
) {
}