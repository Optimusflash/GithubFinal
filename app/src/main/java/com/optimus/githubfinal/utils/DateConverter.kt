package com.optimus.githubfinal.utils

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Dmitriy Chebotar on 26.05.2020.
 */
class DateConverter {

    @TypeConverter
    fun dateToLong(date: Date): Long{

            return date.time
    }
    @TypeConverter
    fun longToDate(time: Long): Date{
        return Date(time)
    }

}