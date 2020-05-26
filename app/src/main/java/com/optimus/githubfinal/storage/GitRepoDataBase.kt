package com.optimus.githubfinal.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.optimus.githubfinal.model.GitRepository


/**
 * Created by Dmitriy Chebotar on 26.05.2020.
 */

@Database(entities = [GitRepository::class], version = 1, exportSchema = false)
abstract class GitRepoDataBase:RoomDatabase() {
    abstract fun gitRepoDao():GitRepoDao
}