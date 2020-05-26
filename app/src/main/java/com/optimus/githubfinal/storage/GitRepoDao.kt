package com.optimus.githubfinal.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.optimus.githubfinal.model.GitRepository

/**
 * Created by Dmitriy Chebotar on 26.05.2020.
 */

@Dao
interface GitRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<GitRepository>)

    @Query("DELETE FROM GitRepository")
    fun deleteAll()

    @Query("SELECT * FROM GitRepository")
    fun getAll(): LiveData<List<GitRepository>>

    @Query("SELECT * FROM GitRepository WHERE name = :repo AND owner_login = :user LIMIT 1")
    fun getOne(user: String, repo: String): LiveData<GitRepository>

    @Transaction
    fun deleteAllAndInsert(list: List<GitRepository>) {
        deleteAll()
        insertAll(list)
    }
}