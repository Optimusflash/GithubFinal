package com.optimus.githubfinal.extensions

import androidx.lifecycle.MutableLiveData

/**
 * Created by Dmitriy Chebotar on 27.05.2020.
 */

fun <T> mutableLiveData(defaultValue: T? = null ): MutableLiveData<T> {
    val data = MutableLiveData<T>()
    if (defaultValue!=null){
        data.value = defaultValue
    }
    return data
}