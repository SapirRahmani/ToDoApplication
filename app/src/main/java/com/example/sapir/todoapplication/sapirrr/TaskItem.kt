package com.example.sapir.todoapplication.sapirrr

import android.os.Parcel
import android.os.Parcelable
import java.util.*

//@Entity(tableName = "tasks")
data class TaskItem(
    /*@PrimaryKey @ColumnInfo(name = "createDate")*/    var createDate: Date = Date(),
    /*@ColumnInfo(name = "title")                 */    var title: String? = "(No Title)",
    /*@ColumnInfo(name = "description")           */    var description: String,
    /*@ColumnInfo(name = "checked")               */    var checked: Boolean = false
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Date::class.java.classLoader) as Date,
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

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

    companion object CREATOR : Parcelable.Creator<TaskItem> {
        override fun createFromParcel(parcel: Parcel): TaskItem {
            return TaskItem(parcel)
        }

        override fun newArray(size: Int): Array<TaskItem?> {
            return arrayOfNulls(size)
        }
    }


}