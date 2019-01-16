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
    @PrimaryKey
    @ColumnInfo(name = "createDate")
    var createDate: Date = Date(),

    @ColumnInfo(name = "title")
    var title: String? = "(No Title)",

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "checked")
    var checked: Boolean = false

) :
    Parcelable {
    @Ignore
    constructor(parcel: Parcel) : this(
        parcel.readValue(Date::class.java.classLoader) as Date,
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    @Ignore
    constructor() : this(Date(), "", "", false)

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
        dest.writeValue(createDate)
        dest.writeValue(checked)
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }


}