package com.optimus.githubfinal.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dmitriy Chebotar on 26.05.2020.
 */

fun Date.formatDate(pattern: String = "dd.MM.yyyy"): String{
    val simpleDateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return simpleDateFormat.format(this)
}