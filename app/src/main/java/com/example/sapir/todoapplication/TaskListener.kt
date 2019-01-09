package com.example.sapir.todoapplication

import java.text.FieldPosition

interface TaskListener {
    fun onMultiSelect(holder: MyAdapter.MyViewHolder)
    fun onEditTask(holder: MyAdapter.MyViewHolder)
}