package com.example.sapir.todoapplication

import java.text.FieldPosition

interface TaskListener {
    fun onTaskChecked (position: Int)
}