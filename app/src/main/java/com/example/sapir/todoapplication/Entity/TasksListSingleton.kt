package com.example.sapir.todoapplication.Entity

import android.content.res.Resources
import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.sapir.todoapplication.Listener.NavigationListener
import com.example.sapir.todoapplication.MyAdapter
import com.example.sapir.todoapplication.R

class TasksListSingleton() {


    companion object {
        private lateinit var INSTANCE: TasksListSingleton

        val instance: TasksListSingleton get() = INSTANCE
        private val M_TASKS_LIST: ArrayList<Task> = ArrayList<Task>()

        fun initialize() {
            INSTANCE =
                    TasksListSingleton()
        }

        fun activateDeleteSwipeListener(myAdapter: MyAdapter, myRecyclerView: RecyclerView) {

            val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
            ) {
                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    val foregroundView = (viewHolder as MyAdapter.MyViewHolder).rowRelativeLayout

                    getDefaultUIUtil().onDraw(
                        c, recyclerView, foregroundView, dX, dY,
                        actionState, isCurrentlyActive
                    )
                }

                override fun onMove(
                    p0: RecyclerView,
                    p1: RecyclerView.ViewHolder,
                    p2: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                    val position = viewHolder.adapterPosition
                    M_TASKS_LIST.remove(
                        getTaskByPos(
                            position
                        )
                    )
                    myAdapter.notifyItemRemoved(position)
                }
            }

            val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(myRecyclerView);
        }

        private fun activateEditTask(holder: MyAdapter.MyViewHolder, myListener: NavigationListener) {
            val position = holder.adapterPosition
            val task = getTaskByPos(position)
            delete(task)

            myListener.onNavClick(
                Resources.getSystem().getString(R.string.edit_fragment),
                Resources.getSystem().getString(R.string.edit_task_bundle_key),
                task
            )
        }

        fun deleteMany(tasks: ArrayList<Task>) {
            M_TASKS_LIST.removeAll(tasks)
        }

        fun delete(task: Task) {
            M_TASKS_LIST.remove(task)
        }

        fun size(): Int {
            return M_TASKS_LIST.size
        }

        fun add(task: Task) {
            M_TASKS_LIST.add(task)
        }

        fun edit(oldTask: Task, newTask: Task) {
            val index = M_TASKS_LIST.indexOf(oldTask)
            if (index != -1) {
                M_TASKS_LIST[index] = newTask
            }
        }

        fun getTaskByPos(position: Int): Task {
            return M_TASKS_LIST[position]
        }

        fun getTasksList(): ArrayList<Task> {
            return M_TASKS_LIST
        }

    }


}
