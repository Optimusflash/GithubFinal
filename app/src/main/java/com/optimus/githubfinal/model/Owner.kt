package com.optimus.githubfinal.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */


data class Owner(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String
) {
}