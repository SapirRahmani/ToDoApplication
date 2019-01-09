package com.example.sapir.todoapplication

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class ToDoTask(var title: String? = "(No Title)", var description: String, var checked:Boolean = false, var createDate: Date = Date()) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readValue(Date::class.java.classLoader) as Date
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
        dest.writeValue(createDate)
        dest.writeValue(checked)
    }

    companion object CREATOR : Parcelable.Creator<ToDoTask> {
        override fun createFromParcel(parcel: Parcel): ToDoTask {
            return ToDoTask(parcel)
        }

        override fun newArray(size: Int): Array<ToDoTask?> {
            return arrayOfNulls(size)
        }
    }


}