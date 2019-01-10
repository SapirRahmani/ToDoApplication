package com.example.sapir.todoapplication.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_to_do_list_tasks.*
import android.support.v7.widget.DividerItemDecoration
import com.example.sapir.todoapplication.*
import com.example.sapir.todoapplication.Entity.Task
import com.example.sapir.todoapplication.Entity.TasksListSingleton
import com.example.sapir.todoapplication.Listener.TaskListener
import com.example.sapir.todoapplication.databinding.FragmentToDoListTasksBinding
import kotlin.collections.ArrayList


class TasksListFragment : BaseFragment(),
    TaskListener {

    private var fragmentTodoListTasksBinding: FragmentToDoListTasksBinding? = null
    private lateinit var mAdapter: MyAdapter

    companion object {

        fun newInstance(): TasksListFragment {
            return TasksListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTodoListTasksBinding = FragmentToDoListTasksBinding.inflate(
            inflater, container, false
        )
        //val emptyTask = Task(null, "", false)

        //fragmentTodoListTasksBinding?.task = emptyTask
        return fragmentTodoListTasksBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mRecyclerView = rv_todo_list as RecyclerView

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(itemDecoration)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = MyAdapter(
            TasksListSingleton.getTasksList(),
            this
        )
        mRecyclerView.adapter = mAdapter

        // activateDeleteSwipeListener one item or multi
        TasksListSingleton.activateDeleteSwipeListener(
            mAdapter,
            mRecyclerView
        )
        deleteMultiSelectItems()

        //add
        fab_add_task?.setOnClickListener { myListener.onNavClick(NewTaskFragment.toString(), null) }
    }

    // activateDeleteSwipeListener many
    private var selectedMode: Boolean = false
    private var selectedItemsList: ArrayList<Task> = ArrayList()
    private var selectedPositionsList: ArrayList<Int> = ArrayList()

    @SuppressLint("RestrictedApi")
    private fun deleteMultiSelectItems() {

        //activateDeleteSwipeListener multi select

        fragmentTodoListTasksBinding?.fabRemoveTask?.setOnClickListener {

            TasksListSingleton.deleteMany(selectedItemsList)
            for (pos: Int in selectedPositionsList) {
                mAdapter.notifyItemRemoved(pos)
            }
            fragmentTodoListTasksBinding?.fabRemoveTask?.visibility = View.INVISIBLE
            selectedMode = false
        }
    }


    //  short click after long click
    private fun onClickInSelectMode(holder: MyAdapter.MyViewHolder) {
        val position = holder.adapterPosition
        val currTask = TasksListSingleton.getTaskByPos(position)
        if (selectedItemsList.contains(currTask)) {
            selectedItemsList.remove(currTask)
            selectedPositionsList.remove(position)
            holder.rowRelativeLayout.setBackgroundResource(R.color.white)

        } else {
            selectedItemsList.add(currTask)
            selectedPositionsList.add(position)
            holder.rowRelativeLayout.setBackgroundResource(R.color.colorSelected)
        }

    }

    // multi select
    @SuppressLint("RestrictedApi")
    override fun onMultiSelect(holder: MyAdapter.MyViewHolder) {
        holder.itemView.setOnLongClickListener(View.OnLongClickListener setOnLongClickListener@{
            if (!selectedMode) {
                fab_remove_task?.visibility = View.VISIBLE
                selectedMode = true
                val selectedPosition = holder.adapterPosition
                selectedItemsList.add(
                    TasksListSingleton.getTaskByPos(
                        selectedPosition
                    )
                )
                selectedPositionsList.add(selectedPosition)
                holder.rowRelativeLayout.setBackgroundResource(R.color.colorSelected)

            }
            return@setOnLongClickListener true
        })
    }

    override fun onTaskClicked(holder: MyAdapter.MyViewHolder) {
        holder.itemView.setOnClickListener {
            if (selectedMode) {
                onClickInSelectMode(holder)
            } else {
                TasksListSingleton.activateDeleteSwipeListener(
                    mAdapter,
                    rv_todo_list
                )

            }
        }
    }
}

