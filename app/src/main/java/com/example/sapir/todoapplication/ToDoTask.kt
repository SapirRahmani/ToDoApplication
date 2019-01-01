package com.example.sapir.todoapplication

import java.util.*

data class ToDoTask(var title: String?, var description: String,val checked:Boolean = false, val createDate: Date = Date()) {


}