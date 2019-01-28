package com.example.sapir.todoapplication

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import java.util.*

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "createDate")
    var createDate: Date = Date(),

    @ColumnInfo(name = "title")
    var title: String? = "(No Title)",

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "checked")
    var checked: Boolean = false

) {
    constructor() : this(0, Date(), "", "", false)

}