package com.example.sapir.todoapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_to_do_list_tasks.*
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.sapir.todoapplication.databinding.FragmentToDoListTasksBinding
import android.widget.AdapterView.OnItemClickListener
import java.util.*
import kotlin.collections.ArrayList


class TasksListFragment : BaseFragment(), TaskListener {

/*    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager*/

    private var fragmentTodoListTasksBinding: FragmentToDoListTasksBinding? = null
    private val items = TasksListSingleton.instance.TasksList

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

        val mRecyclerView = rv_todo_list as RecyclerView

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(itemDecoration)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        val adapter = MyAdapter(items, this)
        mRecyclerView.adapter = adapter

        //delete one item with swipe
        deleteItemOnSwipe(items, adapter, mRecyclerView)

        //edit
        mRecyclerView.setOnClickListener {
            val itemPosition = mRecyclerView.getChildLayoutPosition(view)
            val item = items[itemPosition]
            val args = editTask()
            mRecyclerView.setOnClickListener { myListener.onNavClick(getString(R.string.edit_fragment), args) }
        }

        //delete multi select
        fab_remove_task?.setOnClickListener {
            var itemsCopy: ArrayList<ToDoTask> = ArrayList()
            itemsCopy.addAll(items.filterNotNull())

            fab_remove_task?.visibility = View.INVISIBLE
            selectedMode = false

            for (position: Int in selectedPositionsList) {
                var removedTaskPosition = itemsCopy[position]
                items.remove(removedTaskPosition)

                adapter.notifyItemChanged(position)
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
            }
        }

        //add
        fab_add_task?.setOnClickListener { myListener.onNavClick(NewTaskFragment.toString(), null) }
    }

    private fun editTask(): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(getString(R.string.edit_task_bundle_key), fragmentTodoListTasksBinding?.task)
        return bundle
    }

    private var selectedMode: Boolean = false
    private var selectedItemsList: ArrayList<ToDoTask> = ArrayList()
    private var selectedPositionsList: ArrayList<Int> = ArrayList()

    @SuppressLint("RestrictedApi")
    override fun onSelectedMode(holder: MyAdapter.MyViewHolder) {


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

    private fun deleteItemOnSwipe(items: ArrayList<ToDoTask>, adapter: MyAdapter, mRecyclerView: RecyclerView) {

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                //adapter.items.remove(items[position])
                items.remove(items[position])
                adapter.notifyDataSetChanged()
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }


}

