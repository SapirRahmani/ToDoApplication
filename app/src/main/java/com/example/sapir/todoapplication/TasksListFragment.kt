package com.example.sapir.todoapplication

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_to_do_list_tasks.*
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.sapir.todoapplication.databinding.FragmentToDoListTasksBinding
import kotlin.collections.ArrayList


class TasksListFragment : BaseFragment(), TaskListener {

    private var fragmentTodoListTasksBinding: FragmentToDoListTasksBinding? = null

    private val items = TasksListSingleton.instance.TasksList
    private lateinit var mRecyclerView: RecyclerView
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
        val emptyTask = ToDoTask(null, "", false)

        fragmentTodoListTasksBinding?.task = emptyTask
        return fragmentTodoListTasksBinding?.root
    }


    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerView = rv_todo_list as RecyclerView

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(itemDecoration)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = MyAdapter(items, this)
        mRecyclerView.adapter = mAdapter

        // delete one item or multi
        deleteOneItemOnSwipe()
        deleteMultiSelectItems()

        //add
        fab_add_task?.setOnClickListener { myListener.onNavClick(NewTaskFragment.toString(), null) }
    }

    // delete one
    private var selectedMode: Boolean = false
    private var selectedItemsList: ArrayList<ToDoTask> = ArrayList()
    private var selectedPositionsList: ArrayList<Int> = ArrayList()

    private fun deleteOneItemOnSwipe() {

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

            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                //adapter.items.remove(items[position])
                items.remove(items[position])
                mAdapter.notifyDataSetChanged()
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    // delete many
    @SuppressLint("RestrictedApi")
    private fun deleteMultiSelectItems() {

        //delete multi select
        fragmentTodoListTasksBinding?.fabRemoveTask?.setOnClickListener {
            var itemsCopy: ArrayList<ToDoTask> = ArrayList()
            itemsCopy.addAll(items.filterNotNull())

            fragmentTodoListTasksBinding?.fabRemoveTask?.visibility = View.INVISIBLE
            selectedMode = false

            for (position: Int in selectedPositionsList) {
                var removedTaskPosition = itemsCopy[position]
                items.remove(removedTaskPosition)

                mAdapter.notifyItemChanged(position)
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }
        }
    }

    //edit
    private fun editTask(item: ToDoTask): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(getString(R.string.edit_task_bundle_key), item)
        return bundle
    }

    override fun onEditTask(holder: MyAdapter.MyViewHolder) {
        holder.itemView.setOnClickListener {
            if (!selectedMode) {
                val itemPosition = holder.adapterPosition
                val item = items[itemPosition]
                val args = editTask(item)
                myListener.onNavClick(getString(R.string.edit_fragment), args)
            }
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
                selectedItemsList.add(items[selectedPosition])
                selectedPositionsList.add(selectedPosition)
                holder.rowRelativeLayout.setBackgroundResource(R.color.colorSelected)

            }
            return@setOnLongClickListener true
        })
        holder.itemView.setOnClickListener {
            var currTask = items[holder.adapterPosition]
            if (selectedMode) {
                if (selectedItemsList.contains(currTask)) {
                    selectedItemsList.remove(currTask)
                    selectedPositionsList.remove(holder.adapterPosition)
                    holder.rowRelativeLayout.setBackgroundResource(R.color.white)

                } else {
                    selectedItemsList.add(currTask)
                    selectedPositionsList.add(holder.adapterPosition)
                    holder.rowRelativeLayout.setBackgroundResource(R.color.colorSelected)
                }
            }
        }
    }

}

