package com.example.sapir.todoapplication.Util

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.sapir.todoapplication.Listener.NavigationListener
import com.example.sapir.todoapplication.RecyclerView.MyAdapter
import com.example.sapir.todoapplication.Room.TaskViewModel

object TaskUtil {

    @JvmStatic
    fun activateDeleteSwipeListener(myAdapter: MyAdapter, myRecyclerView: RecyclerView, taskViewModel: TaskViewModel) {

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
                val taskToRemove = myAdapter.getTaskByPos(position)

                taskViewModel.delete(taskToRemove)
                myAdapter.notifyItemRemoved(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(myRecyclerView)
    }

    @JvmStatic
    fun activateEditTask(
        holder: MyAdapter.MyViewHolder,
        adapter: MyAdapter,
        myListener: NavigationListener,
        taskViewModel: TaskViewModel
    ) {
        val position = holder.adapterPosition
        val taskToRemove = adapter.getTaskByPos(position)
        taskViewModel.delete(taskToRemove)
        adapter.notifyItemRemoved(position)

        myListener.onNavClick(
            "EditTaskFragment",
            "Edit",
            taskToRemove
        )
    }

}