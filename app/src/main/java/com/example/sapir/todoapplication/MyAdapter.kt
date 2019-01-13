package com.example.sapir.todoapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sapir.todoapplication.sapirrr.TaskItem
import com.example.sapir.todoapplication.Listener.TaskListener
import kotlinx.android.synthetic.main.todo_task_item.view.*


class MyAdapter(private val items: ArrayList<TaskItem>, private val listener: TaskListener) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle = view.tv_title
        var tvDescription = view.tv_description
        var tvCreatedDate = view.tv_create_time
        var cbChecked = view.cb_task
        var rowRelativeLayout = view.rl_task_item
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_task_item, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        items.sortByDescending { it -> it.createDate }

        val task = items[position]
        holder.tvTitle.text = task.title
        holder.tvDescription.text = task.description
        holder.cbChecked.isChecked = task.checked
        holder.tvCreatedDate.text = android.text.format.DateFormat.format("dd-MM-yyyy HH:mm:ss", task.createDate)


        changeColorOnTaskChecked(position, items, holder)
        listener.onTaskLongClicked(holder)
        listener.onTaskClicked(holder)
    }

    private fun changeColorOnTaskChecked(position: Int, items: ArrayList<TaskItem>, holder: MyViewHolder) {
        if (items[position].checked) {
            holder.rowRelativeLayout.setBackgroundResource(R.color.colorLight)
        }

        holder.cbChecked.setOnCheckedChangeListener { _, isChecked ->
            items[position].checked = isChecked
            if (isChecked) {
                holder.rowRelativeLayout.setBackgroundResource(R.color.colorLight)
            } else {
                holder.rowRelativeLayout.setBackgroundResource(R.color.white)
            }
            notifyItemChanged(position, items[position])
        }
    }



}