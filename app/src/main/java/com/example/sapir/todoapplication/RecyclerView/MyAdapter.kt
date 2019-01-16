package com.example.sapir.todoapplication.RecyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sapir.todoapplication.Listener.TaskListener
import com.example.sapir.todoapplication.R
import com.example.sapir.todoapplication.Task
import kotlinx.android.synthetic.main.todo_task_item.view.*


class MyAdapter(context: Context, private val listener: TaskListener) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tasks = emptyList<Task>()

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
    ): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_task_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        tasks.sortedByDescending{ it -> it.createDate }

        val task = tasks[position]
        holder.tvTitle.text = task.title
        holder.tvDescription.text = task.description
        holder.cbChecked.isChecked = task.checked
        holder.tvCreatedDate.text = android.text.format.DateFormat.format("dd-MM-yyyy HH:mm:ss", task.createDate)


        changeColorOnTaskChecked(position, tasks, holder)
        listener.onTaskLongClicked(holder)
        listener.onTaskClicked(holder)
    }

    internal fun setTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    fun getTaskByPos(pos: Int): Task {
        return this.tasks[pos]
    }

    private fun changeColorOnTaskChecked(position: Int, items: List<Task>, holder: MyViewHolder) {
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