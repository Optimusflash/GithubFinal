package com.optimus.githubfinal.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.optimus.githubfinal.utils.DateConverter
import java.util.*

/**
 * Created by Dmitriy Chebotar on 25.05.2020.
 */

@Entity
@TypeConverters(DateConverter::class)
data class GitRepository(
    @PrimaryKey
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("created_at") val createdDate: Date,
    @SerializedName("updated_at") val updatedDate: Date,
    @SerializedName("stargazers_count") val starsCount: Int,
    @SerializedName("language") val progLanguage: String?,
    @SerializedName("forks_count") val forksCount: Int,
    @Embedded(prefix = "owner_")
    @SerializedName("owner") val owner: Owner
) {
}