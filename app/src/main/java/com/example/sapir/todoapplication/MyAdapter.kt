package com.example.sapir.todoapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.todo_task_item.view.*
import android.widget.LinearLayout
import java.text.DateFormat


class MyAdapter(private val items: ArrayList<ToDoTask>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle = view.tv_title
        var tvDescription = view.tv_description
        var tvCreatedDate= view.tv_create_time
        var cbChecked = view.cb_task
        var rowRelativeLayout = view.rl_task_item
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_task_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val task = items[position]
        holder.tvTitle.text = task.title
        holder.tvDescription.text = task.description
        holder.cbChecked.isChecked = task.checked
        holder.tvCreatedDate.text = task.createDate.toString()

        holder.cbChecked.setOnClickListener {
            if (!task.checked)
                holder.rowRelativeLayout.setBackgroundResource(R.color.colorLight)
            else {
                holder.rowRelativeLayout.setBackgroundResource(R.color.white)
            }
        }
    }

    override fun getItemCount() = items.size
}