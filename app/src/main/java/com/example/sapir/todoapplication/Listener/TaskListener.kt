package com.example.sapir.todoapplication.Listener

import com.example.sapir.todoapplication.MyAdapter

interface TaskListener {
    fun onTaskClicked(holder: MyAdapter.MyViewHolder)
    fun onMultiSelect(holder: MyAdapter.MyViewHolder)
}