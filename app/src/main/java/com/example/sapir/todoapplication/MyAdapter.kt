package com.example.sapir.todoapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.todo_task_item.view.*

class MyAdapter(private val items: ArrayList<ToDoTask>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle  = view.tv_title
        var tvDescription  = view.tv_description
        var cbChecked  = view.cb_task
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_task_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var task =  items[position]
        holder.tvTitle.text = task.title
        holder.tvDescription.text = task.description
        holder.cbChecked.isChecked= task.checked
    }

    override fun getItemCount() = items.size
}