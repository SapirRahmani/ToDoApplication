package com.example.sapir.todoapplication.Converter

import android.arch.persistence.room.TypeConverter
import java.util.*


object Converters {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date): Long {
        return (date.time).toLong()
    }
}