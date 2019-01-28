package com.example.sapir.todoapplication.Fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_to_do_list_tasks.*
import android.support.v7.widget.DividerItemDecoration
import android.view.*
import com.example.sapir.todoapplication.*
import com.example.sapir.todoapplication.Task
import com.example.sapir.todoapplication.Listener.TaskListener
import com.example.sapir.todoapplication.RecyclerView.MyAdapter
import com.example.sapir.todoapplication.Util.TaskUtil
import com.example.sapir.todoapplication.databinding.FragmentToDoListTasksBinding
import kotlin.collections.ArrayList

class TasksListFragment : BaseFragment(), TaskListener {

    private var fragmentTodoListTasksBinding: FragmentToDoListTasksBinding? = null
    private lateinit var mAdapter: MyAdapter

    // activateDeleteSwipeListener many
    private var selectedMode: Boolean = false
    private var selectedTasksList: ArrayList<Task> = ArrayList()
    private var selectedPositionsList: ArrayList<Int> = ArrayList()

    private var optionsMenu: Menu? = null

    companion object {

        fun newInstance(): TasksListFragment {
            return TasksListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTodoListTasksBinding = FragmentToDoListTasksBinding.inflate(
            inflater, container, false
        )
        setHasOptionsMenu(true)

        fragmentTodoListTasksBinding?.task = Task()
        return fragmentTodoListTasksBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerView = rv_todo_list as RecyclerView

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(itemDecoration)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = MyAdapter(context!!, this)

        mTaskViewModel.allTasks?.observe(viewLifecycleOwner, Observer { tasks ->
            // Update the cached copy of the tasks in the adapter.
            tasks?.let {
                mAdapter.setTasks(it)
            }
        })


        mRecyclerView.adapter = mAdapter

        // activateDeleteSwipeListener one item or multi
        TaskUtil.activateDeleteSwipeListener(this, mRecyclerView)

        //add
        fab_add_task?.setOnClickListener {
            myListener.onNavClick(NewTaskFragment.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu, menu)
        if (menu != null)
            optionsMenu = menu
    }


    @SuppressLint("RestrictedApi")
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_delete -> {
                mTaskViewModel.deleteMany(selectedTasksList)
                for (pos: Int in selectedPositionsList) {
                    mAdapter.notifyItemRemoved(pos)
                }
                item.isVisible = false
                selectedMode = false
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //  short click after long click
    private fun onClickInSelectMode(holder: MyAdapter.MyViewHolder) {
        val position = holder.adapterPosition
        val currTask = mAdapter.getTaskByPos(position)
        if (selectedTasksList.contains(currTask)) {
            selectedTasksList.remove(currTask)
            selectedPositionsList.remove(position)
            holder.rowRelativeLayout.setBackgroundResource(R.color.white)

        } else {
            selectedTasksList.add(currTask)
            selectedPositionsList.add(position)
            holder.rowRelativeLayout.setBackgroundResource(R.color.colorSelected)
        }

    }

    @SuppressLint("RestrictedApi")
    override fun onTaskLongClicked(holder: MyAdapter.MyViewHolder) {
        holder.itemView.setOnLongClickListener(View.OnLongClickListener setOnLongClickListener@{
            if (!selectedMode) {
                val item = optionsMenu?.findItem(R.id.action_delete)
                item?.isVisible = true
                selectedMode = true
                val selectedPosition = holder.adapterPosition
                selectedTasksList.add(
                    mAdapter.getTaskByPos(
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
                TaskUtil.activateEditTask(this, holder)

            }
        }
    }

    override fun onDelete(position: Int) {
        val taskToRemove = mAdapter.getTaskByPos(position)
        mTaskViewModel.delete(taskToRemove)
        mAdapter.notifyItemRemoved(position)
    }

    override fun onEdit(position: Int) {
        val taskToEdit = mAdapter.getTaskByPos(position)
        mTaskViewModel.editTask = taskToEdit

        mAdapter.notifyItemRemoved(position)

        myListener.onNavClick(
            "EditTaskFragment"
        )
    }


}
