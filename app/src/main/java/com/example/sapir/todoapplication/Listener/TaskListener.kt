package com.example.sapir.todoapplication.Listener

import com.example.sapir.todoapplication.RecyclerView.MyAdapter

interface TaskListener {

    fun onTaskClicked(holder: MyAdapter.MyViewHolder)
    fun onTaskLongClicked(holder: MyAdapter.MyViewHolder)
    fun onDelete(position: Int)
    fun onEdit(position: Int)
}